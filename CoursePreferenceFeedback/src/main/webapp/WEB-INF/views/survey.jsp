<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        padding: 10px 18px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 30%;
    border-radius:5px;
    font-size:15px;
}
.model1{
 border: 5px solid #888;
 text-align:center;
 width:50%;
 padding:20px;
 margin:auto;
 background-color:#e6e6ff;
}


button:hover {
    opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {

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
    width: 70%; /* Could be more or less, depending on screen size */
	padding:10px;
 
}
.pstyle{
text-align:center;
  font-size: 25px;
}
.error{
color:red;
}

</style>

<body style="background-color:#000033">
    <div class="container">

<form class="modal-content animate" action="/EmployeeFormMVC/save.html" method="post">
<p class="pstyle">Survey</p>
<div class="pstyle">
<table style="font-size:20px;">

<tr>
	<td valign="top"> Skills: </td>
	<td>
	<c:forEach items="${results}" var="result">
		<input type="checkbox" name="skills" value="${result.name}"> ${ result.name } <br>
	</c:forEach>
	</td>
</tr>
<tr>
	<td>Enter course(comma separated values)</td>
	<td><input type="text" name="skillInput" placeholder="Enter Skill"></td>	
</tr>
</table>
<br>
 <button type="submit" style="font-size:15px;">Submit</button>
  <a href="/CoursePreferenceFeedback/survey"><button type="button" class="cancelbtn">Cancel</button></a>
 </div>

</form>
</div>
</body>
</html>