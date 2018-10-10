<html>
<body>
    <h2>Jersey RESTful Web Application!</h2>
    <p><a href="webapi/myresource">Jersey resource</a>
    <p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
    for more information on Jersey!
        Choose file to upload<br>
    <form action="http://localhost:8080/api/files" method="post" enctype="multipart/form-data">
        <input name="file" id="filename" type="file" /><br><br>
        <button name="submit" type="submit">Upload</button>
    </form>
</body>
</html>
