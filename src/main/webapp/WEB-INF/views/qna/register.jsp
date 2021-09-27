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
                            <h1 class="h4 text-gray-900 mb-4">REGISTER</h1>
                        </div>
                        <form id="form1" action="/qna/register" method="post">
                            <div class="form-group">
                                TITLE
                                <input type="text" name="title" class="form-control form-control-user" id="title">
                            </div>
                            <div class="form-group">
                                WRITER
                                <input type="text" name="writer" class="form-control form-control-user" id="writer">
                            </div>
                            <div class="form-group">
                                CONTENT
                                <textarea name="content" class="form-control form-control-user" rows="5" id="content"></textarea>
                            </div>
                            <div class="form-group">
                            <button type="submit" id="submitBtn" class="btn btn-primary btn-user btn-block">
                                문의사항 등록하기
                           </button>
                            </div>
                        <hr>
                        </form>
                    </div>
                </div>

                <label for="exampleInputFile" class="p-4">첨부파일 등록</label>
                <div class="input-group p-2">
                    <div class="custom-file">
                        <input type="file" name="uploadFiles" class="custom-file-input" id="exampleInputFile" multiple>
                        <label class="custom-file-label" for="exampleInputFile">파일을 선택하세요</label>
                    </div>
                    <div class="input-group-append">
                        <span class="input-group-text" id="uploadBtn">첨부파일 화면에 업로드하기</span>
                    </div>
                </div>
                <div class="uploadResult">

                </div>
            </div>
        </div>
    </div>

</div>

<!--푸터 붙여넣기( 앞으로 이거 긁어 쓰세요 ) -->
<%@ include file="../includes/footer.jsp" %>

<script>
    const uploadResultDiv = document.querySelector(".uploadResult")

    document.querySelector("#uploadBtn").addEventListener("click", (e)=>{

        const formData = new FormData()
        const fileInput = document.querySelector("input[name='uploadFiles']")

        for(let i = 0; i<fileInput.files.length; i++){
            formData.append("uploadFiles",fileInput.files[i])
        }

        console.log(formData)

        const headerObj = {headers:{'Content-Type':'multipart/form-data'}}

        axios.post("/upload",formData, headerObj).then((response)=>{
            const arr = response.data()
            console.log(arr)

            let str = ""
            for(let i = 0; i <arr.length; i++){
                const {uuid, fileName, uploadPath, image, thumbnail, fileLink} = {...arr[i]}

                if(image){

                }
            }
        })
    })

    document.querySelector("#submitBtn").addEventListener("click", (e)=> {
        e.stopPropagation()
        e.preventDefault()
        const form1 = document.querySelector("#form1")
        form1.submit()
    }, false)

</script>
</body>
</html>