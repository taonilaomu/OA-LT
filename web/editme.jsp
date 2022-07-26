<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<p>爱好</p>
<div id="hobby" style="height: 100px;border: 1px solid red">

</div>
<button><a href="secondStep.html">第二步</a></button>
<script>
    window.onload = function () {
        gethobby();
    }

    function gethobby() {
        let request = new XMLHttpRequest();
        request.onreadystatechange = function () {
            if (request.readyState == 4 && request.status == 200) {
                let hobbies = request.responseText;
                let hobbyDiv = document.getElementById("hobby");
                for (let i in hobbies) {
                    let childNode = document.createElement("p");
                    childNode.innerText = hobbies[i];
                    hobbyDiv.appendChild(childNode);
                }
            }
        }
        request.open("", "GET");
        request.send();
    }
</script>
</body>
</html>