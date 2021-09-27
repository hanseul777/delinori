<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--헤더 붙여넣기( 앞으로 이거 긁어 쓰세요 ) -->
<%@ include file="../includes/header.jsp" %>

<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">DELI REGISTER</h1>
                        </div>
                        <form id="form1" action="/member/deliregister" method="post">
                            <div class="form-group">
                                <input type="text" name="mid" class="form-control form-control-user" id="mid"
                                       placeholder="ID">
                            </div>
                            <div class="form-group">
                                <input type="text" name="mname" class="form-control form-control-user" id="mname"
                                       placeholder="NAME">
                            </div>
                            <div class="form-group">
                                <input type="text" name="mpw" class="form-control form-control-user" id="mpw"
                                       placeholder="PASSWORD">
                            </div>
                            <div class="form-group">
                                <input type="text" name="maddress" class="form-control form-control-user" id="maddress"
                                       placeholder="ADDRESS">
                            </div>
                            <div class="form-group">
                                <input type="text" name="memail" class="form-control form-control-user" id="memail"
                                       placeholder="EMAIL">
                            </div>
                            <div class="form-group">
                                <input type="text" name="mphone" class="form-control form-control-user" id="mphone"
                                       placeholder="PHONE">
                            </div>
                            <div class="input-group">
                                <input type="file" class="form-control form-control-user" name="uploadFiles" id="InputFile" multiple>
                            </div>
                            <div class="input-group-append">
                                <span class="input-group-text" id="uploadBtn">Upload</span>
                            </div>
                            <div class="uploadResult"></div>
                            <div class="temp"></div>
                            <div class="form-group">
                                <hr>
                                <button type="submit" id="submitBtn" class="btn btn-primary btn-user btn-block">
                                    Deli Register Account
                                </button>
                            </div>
                            <hr>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!--푸터 붙여넣기( 앞으로 이거 긁어 쓰세요 ) -->
<%@ include file="../includes/footer.jsp" %>

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


    document.querySelector("#submitBtn").addEventListener("click", (e)=> {
        e.stopPropagation()
        e.preventDefault()
        const form1 = document.querySelector("#form1")
        const fileDivArr=uploadResultDiv.querySelectorAll("div")

        if(!fileDivArr){//만약 첨부파일이 없다면 그걸로 끝이고 바로 등록
            form1.submit()
            return
        }
        let str="";
        for(let i=0; i<fileDivArr.length; i++){//첨부파일 1개당 배열 1개니까 배열 길이가 파일 개수와 같음
            //console.log(fileDivArr[i])
            const target=fileDivArr[i]
            const uuid=target.getAttribute("data-uuid")
            const fileName=target.getAttribute("data-filename")
            const uploadPath=target.getAttribute("data-uploadpath")
            const image=target.getAttribute("data-image")

            //input 태그 4개 생성
            str+=`<input type='hidden' name='files[\${i}].uuid' value='\${uuid}'>`
            str+=`<input type='hidden' name='files[\${i}].fileName' value='\${fileName}'>`
            str+=`<input type='hidden' name='files[\${i}].uploadPath' value='\${uploadPath}'>`
            str+=`<input type='hidden' name='files[\${i}].image' value='\${image}'>`
        }
        document.querySelector(".temp").innerHTML+=str
        form1.submit()
    }, false)

</script>
</body>
</html>