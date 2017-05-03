<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<style>
/* Full-width input fields */
input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

/* Set a style for all buttons */
button {
    background-color: #000066;
    color: white;
    padding: 8px 18px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 60%;
    border-radius:5px;
    font-size:15px;
}
.model1{
 border: 5px solid #888;
 text-align:center;
 width:50%;
 padding:20px;
 margin:auto;
 background-color:white;
}


button:hover {
    opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
    width: 100%;
    padding: 10px 18px;
    background-color: #b30000;
      font-size:15px;
}


.container {
    padding: 10px;
 
    
}




/* Modal Content/Box */
.modal-content {
    background-color: #fefefe;
    margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
    border: 5px solid #888;
    width: 80%; /* Could be more or less, depending on screen size */
	padding:10px;
    height:70%
 
}
.pstyle{
text-align:center;
  font-size: 20px;
  
}
.error{
color:red;
}

</style>

<body style="background-color:#000033">
    <div class="container">

<form class="modal-content animate" action="/CoursePreferenceFeedback/save.html" method="post">
<p class="pstyle" style="font-size:25px"><b>Survey</b></p>
<div class="pstyle">

<table  width=100%>
<tr>

<td width=28%><p class="pstyle" style="text-align:left"><b>Course Preference</b></p></td>
<td width=28%>
<a href="/CoursePreferenceFeedback/survey"> <button type="button">Enter Survey</button></a></td>
<td><p class="error"style="text-align:left">${surveyFailure}</p></td>
</tr>
</table>
<br><br><br>
<a href="/CoursePreferenceFeedback/Logout"> <button style="width:25%" type="button">Logout</button></a>
</div>

</form>
</div>
</body>
</html>