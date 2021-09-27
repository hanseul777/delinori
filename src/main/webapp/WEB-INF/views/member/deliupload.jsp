<%--
  Created by IntelliJ IDEA.
  User: repar
  Date: 2021-09-07
  Time: 오후 2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<input type="file" name="uploadFiles" multiple><button id="uploadBtn">UPLOAD</button>
<div class="uploadResult">

</div>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<script>
    const uploadResultDiv=document.querySelector(".uploadResult")//div로 담아서 전달

    document.querySelector("#uploadBtn").addEventListener("click",(e)=>{
        const formData=new FormData()
        const fileInput=document.querySelector("input[name='uploadFiles']")

        for(let i=0; i<fileInput.files.length; i++){
            formData.append("uploadFiles", fileInput.files[i])
            //uploadFiles란 이름으로 컨트롤러에 보내준다
        }

        //console.log(fileInput)
        //console.dir(fileInput)//배열로 나옴
        //console.dir(formData)
        console.log(formData)

        const headerObj={headers:{'Content-Type': 'multipart/form-data'}}
        axios.post("/upload",formData,headerObj).then((response)=>{//버튼 누르면 upload 호출
            const arr=response.data
            console.log(arr)
            let str=""
            for(let i=0; i<arr.length; i++){//배열이니까 루프로 돌림
                const {uuid, fileName, uploadPath, image, thumbnail, fileLink}={...arr[i]}//스프레드 연산자로 쫙 펼침
                if(image){
                    str+=`<div data-uuid='\${uuid}'><img src='/viewFile?file=\${thumbnail}'/><span>\${fileName}</span>
                          <button onclick="javascript:removeFile('\${fileLink}', this)">X</button></div>`
                }else {
                    str+=`<div data-uuid='\${uuid}'><a href='/downFile?file=\${fileLink}'>\${fileName}</a></div>`
                }
            }
            uploadResultDiv.innerHTML+=str//업로드를 여러 번 할 수 있으니까 누적
        })

    },false)

    function removeFile(fileLink, ele){
        console.log(fileLink)
        axios.post("/removeFile", {fileName:fileLink}).then(response=>{
            const targetDiv=ele.parentElement//버튼의 상위 요소를 가져 옴
            targetDiv.remove()//삭제
        })
    }


</script>
</body>
</html>
