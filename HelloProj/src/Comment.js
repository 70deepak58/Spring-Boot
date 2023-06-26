import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

function CommentPage() {
  const { productId } = useParams();
  const [comments, setComment] = useState(null);
  const [loading, setLoading] = useState(true);
  const [emjArr, setEmjArrHelp] = useState({ "property": "false" });
  const [replyfield, setReplyField] = useState('');
  const [emj, setEmjHelp] = useState(false);
  const [cmtfield, setCmtField] = useState('');
  const [emjidshow, setEmjIdShow] = useState(false);
  const [emjArrIdShow, setEmjArrIdShowHelp] = useState({ "property": "false" });

  //note note important for emoji
  // {comment.emoji.map(element => <span>{String.fromCodePoint(element.substring(element.length-7))}</span>)}
  //use above for multiple options for single user

  //handle comment field
  const handleInputChange = (event) => {
    setCmtField(event.target.value);
  };

  //handle reply field
  const handleReplyInputChange = (event) => {
    setReplyField(event.target.value);
  };

  //validate comment field
  const validate = () => {
    if (cmtfield.trim() === '') {
      return false;
    }
    return true;
  };

  //validate reply field
  const validateReply = () => {
    if (replyfield.trim() === '') {
      return false;
    }
    return true;
  };

  //react use effect
  useEffect(() => {
    fetchComments();
    handleAddComment();
  }, []);

  const MouseOver = (event) => {
    let currid = event.currentTarget.id;
    setEmjArrIdShowHelp((emjArrIdShow) => ({
      ...emjArrIdShow, [currid]: true
    }));
    setEmjIdShow(true);
  };
  const MouseOut = (event) => {
    let currid = event.currentTarget.id;
    setEmjArrIdShowHelp((emjArrIdShow) => ({
      ...emjArrIdShow, [currid]: false
    }));
    setEmjIdShow(false);
  };


  const handleEmojiHelper = (event) => {
    let currid = event.currentTarget.id;
    if (emjArr[event.currentTarget.id] == false) {
      setEmjArrHelp((emjArr) => ({
        ...emjArr, [currid]: true
      }));
    }
    else {
      setEmjArrHelp((emjArr) => ({
        ...emjArr,
        [currid]: false,
      }));
    }
    setEmjHelp(!emj);
  };






  const fetchComments = async () => {
    try {
      const response = await axios.get(`http://localhost:8082/comment/getPostComment/${localStorage.getItem("productId")}`);
      setComment(response.data);
      console.log("start here");
      console.log(response.data);
      setLoading(false);
      for (let i = 0; i < response.data.length; i++) {
        console.log(response.data[i].id);
        //setEmjArrHelp({...emjArr,[response2.data[i].id]:[false]});
        setEmjArrHelp((emjArr) => ({
          ...emjArr,
          [response.data[i].id]: false,
        }));
        // console.log("delta ", response.data[i].id, " ", emjArr[response.data[i].id])
        // console.log(emjArr)
        //setmeme();
        //abc[response2.data[i].id]="false";
        //console.log(response2.data[i].id," ",abc[response2.data[i].id])
      }
      // if(response.ok){
      //   setLoading(false);
      // }
    } catch (error) {
      setLoading(true);
      console.error(error);
    }
  };

  const handleAddComment = async () => {
    console.log(validate());
    console.log(localStorage.getItem('productId'));
    console.log(localStorage.getItem('empId'));
    console.log(cmtfield);
    if (validate()) {
      try {
        const response = await fetch(`http://localhost:8082/comment/comment`, {  //user role start
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            "product_id": localStorage.getItem('productId'),
            "emp_id": localStorage.getItem('empId'),
            "content": cmtfield,
          }),
        });
        const response2 = await axios.get(`http://localhost:8082/comment/getPostComment/${productId}`);
        setComment(response2.data);
        console.log(response2.data);
        setCmtField("")
        for (let i = 0; i < response2.data.length; i++) {
          console.log(response2.data[i].id);
          setEmjArrHelp((emjArr) => ({
            ...emjArr,
            [response2.data[i].id]: false,
          }));
        }
        if (response.ok) {

        }
        else {

        }
      } catch (error) {

      }
    }
  };

  //for reply comment
  const handleAddReplyComment = async (event) => {
    console.log(event.currentTarget.id);
    let id = event.currentTarget.id.substring(1);
    if (validateReply()) {
      try {
        const response = await fetch(`http://localhost:8082/comment/addReply/${id}/${replyfield}/${localStorage.getItem('empId')}`, {  //user role start
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
        });
        const response2 = await axios.get(`http://localhost:8082/comment/getPostComment/${productId}`);
        setComment(response2.data);
        console.log(response2.data);
        console.log(comments)
        setReplyField("");
        if (response.ok) {

        }
        else {

        }
      } catch (error) {

      }
    }
  };

  //for deleting comment
  const handleDeleteComment = async (event) => {
    console.log(event.currentTarget.id);
    try {
      const response = await fetch(`http://localhost:8082/comment/deleteComent/${event.currentTarget.id}/${localStorage.getItem('empId')}`, {  //user role start
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json' },
      });
      //   const response = await fetch(`http://localhost:8082/comment/deleteComment/${event.currentTarget.id}`, {  //user role start
      //   method: 'DELETE',
      //   headers: { 'Content-Type': 'application/json' },
      // });
      const response2 = await axios.get(`http://localhost:8082/comment/getPostComment/${productId}`);
      setComment(response2.data);
      console.log(response2.data);
      console.log(comments)
      setCmtField("")
      if (response.ok) {

      }
      else {

      }
    } catch (error) {

    }
  }

  //add emoji
  const handleAddEmoji = async (event) => {
    console.log(event.currentTarget.id);
    const optio = { "a": "0x1F44D", "b": "0x1F44E", "c": "0x1F497", "d": "0x1F549", "e": '0x1f603', "f": '0x1F590', "g": '0x1F602', "h": '0x1F608' }
    let emoop = optio[event.currentTarget.id.substring(0, 1)];
    let id = event.currentTarget.id.substring(1);
    console.log(emoop);
    console.log(id);
    console.log(optio);
    try {
      const response = await fetch(`http://localhost:8082/comment/addEmoji/${localStorage.getItem('empId')}/${id}/${emoop}`, {  //user role start
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
      });
      const response2 = await axios.get(`http://localhost:8082/comment/getPostComment/${localStorage.getItem('productId')}`);
      setComment(response2.data);
      console.log(response2.data);
      console.log(comments)
      setCmtField("")
      if (response.ok) {

      }
      else {

      }
    } catch (error) {

    }
  }



  return (
    <div>
      <div>
        <input type="text" value={cmtfield} onChange={handleInputChange} placeholder="Enter Comments" />
        <button onClick={handleAddComment}>Comment</button>
      </div>
      {loading ? (
        <p>Loading...</p>
      ) : comments ? (
        <div>
          {comments.map((comment) => (
            <>
              <div id={comment.id} onDoubleClick={handleEmojiHelper} onMouseOver={MouseOver} onMouseOut={MouseOut}>
                <span><h3>
                  {comment.emp_id + "  "}
                  {comment.content}

                  {Object.keys(comment.emojimap).map(element => <span>{String.fromCodePoint(comment.emojimap[element])}{emjArrIdShow[comment.id] && element}</span>)}
                </h3></span>
              </div>
              <div>
                {emjArr[comment.id] && <span key={"o" + comment.id} >
                  <span id={"a" + comment.id} onClick={handleAddEmoji}>{String.fromCodePoint("0x1F44D")}</span>
                  <span id={"b" + comment.id} onClick={handleAddEmoji}>{String.fromCodePoint("0x1F44E")}</span>
                  <span id={"c" + comment.id} onClick={handleAddEmoji}>{String.fromCodePoint("0x1F497")}</span>
                  <span id={"d" + comment.id} onClick={handleAddEmoji}>{String.fromCodePoint("0x1F549")}</span>
                  <span id={"e" + comment.id} onClick={handleAddEmoji}>{String.fromCodePoint('0x1f603')}</span>
                  <span id={"f" + comment.id} onClick={handleAddEmoji}>{String.fromCodePoint('0x1F590')}</span>
                  <span id={"g" + comment.id} onClick={handleAddEmoji}>{String.fromCodePoint('0x1F602')}</span>
                  <span id={"h" + comment.id} onClick={handleAddEmoji}>{String.fromCodePoint('0x1F608')}</span>
                  <button id={comment.id} onClick={handleDeleteComment}>delete</button>
                  <button id={"r" + comment.id} onClick={handleAddReplyComment}>Reply</button><br />
                  <input type="text" value={replyfield} onChange={handleReplyInputChange} placeholder="Reply Comments" />
                  {comment.reply.map(element => <p>{element}</p>)}
                </span>}
              </div>
            </>
          ))}
        </div>
      ) : (
        <p>No comments found.</p>
      )}
    </div>
  );
}

export default CommentPage;
