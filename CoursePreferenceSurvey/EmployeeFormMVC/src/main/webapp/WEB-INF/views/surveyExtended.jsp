  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
    width: 40%;
    border-radius:5px;
    font-size:15px;
}
.model1{
 border: 5px solid #888;
 text-align:center;
 width:70%;
 padding:20px;
 margin:auto;
 background-color:#e6e6ff;
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


/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
    padding-top: 20px;
}

/* Modal Content/Box */
.modal-content {
    background-color: #fefefe;
    margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
    border: 5px solid #888;
    width: 70%; /* Could be more or less, depending on screen size */
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
<br><br><br><br><br><br><br>

 <div class="modal-content animate"  >
   <p class="pstyle"><b>Preferences</b></p>
    <div class="container">
<form class="modal-content animate" action="/EmployeeFormMVC/surveyExtended" method="post">
<table border=5px width=100%>
<tr>
	<td>Skill</td>
	<td>Alternatives</td>
</tr>
<c:forEach items="${results}" var="result">
<tr>
	<td>${result.skillName}</td>
	<td> 
	<c:forEach items="${result.synonyms}" var="resultSynonym">
		<input type="checkbox" name="skills" value="${resultSynonym}"> ${ resultSynonym } <br>
	</c:forEach>
	<input type="checkbox" name="skills" value="${result.skillName}"> ${ result.skillName } <br> 
	</td>
</tr>
</c:forEach>
</table>

<p style="text-align:center">  <button type="submit" style="font-size:15px;">Submit</button></p>
</form>
    </div>
  </div>
  
 


<script>

</script>

</body>
</html>
