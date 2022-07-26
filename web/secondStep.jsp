<%@page contentType="text/html; ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<p>第二步</p>
<div>
    <select id="province">

    </select>
</div>
<button><a href="editme.html">上一步</a></button>
<button><a href="javascript:void(null)">下一步</a></button>
<%--fixme 点击选中元素--%>
<script>
    window.onload = function () {
        getProvinces();
    }

    function getProvinces() {
        let request = new XMLHttpRequest();
        request.onreadystatechange = function () {
            if (request.readyState == 4 && request.status == 200) {
                let provinces = request.responseText;
                let proviceDiv = document.getElementById("province");
                for (let i in provinces) {
                    let childNode = document.createElement("option");
                    childNode.innerText = hobbies[i];
                    proviceDiv.appendChild(childNode);
                }
            }
        }
        request.open("", "GET");
        request.send();
    }

</script>
</body>
</html>