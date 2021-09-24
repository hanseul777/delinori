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
                            <h1 class="h4 text-gray-900 mb-4">READ</h1>
                        </div>
                        <form id="form1">
                            <input type="hidden" name="page" value="${pageRequestDTO.page}">
                            <input type="hidden" name="size" value="${pageRequestDTO.size}">

                            <div class="form-group">
                                <label for="title">QNO</label>
                                <input type="text" name="qno" class="form-control form-control-user" id="qno" value="<c:out value="${qnaDTO.qno}"></c:out>" readonly>
                            </div>
                            <div class="form-group">
                                <label for="title">TITLE</label>
                                <input type="text" name="title" class="form-control form-control-user" id="title" value="<c:out value="${qnaDTO.title}"></c:out>" readonly>
                            </div>
                            <label for="title">WRITER</label>
                            <div class="form-group">
                                <input type="text" name="writer" class="form-control form-control-user" id="writer" value="<c:out value="${qnaDTO.writer}"></c:out>" readonly>
                            </div>
                            <div class="form-group">
                                <label>CONTENT</label>
                                <textarea name="content" class="form-control form-control-user" id="content" disabled>
                                <c:out value="${qnaDTO.content}"></c:out>
                                </textarea>
                            </div>
                            <hr>
                            <div>
                                <button type="button" class="btn btn-info btnList">LIST</button>
                                <button type="button" class="btn btn-info btnDel">DELETE</button>
                            </div>
                        </form>
                    </div>
                    <!-- 댓글 -->
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">REPLY</h6>
                    </div>
                    <div class="col-xl-12 col-md-12 mb-4">
                        <div class="reply">

                        </div>
                    </div>
                    <!-- 댓글작성 -->
                    <div id="reply-write" class="card o-hidden border-0 shadow-none my-5">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <div class="form-group">
                                        <label for="title">작성자</label>
                                        <input type="text" name="replyer" class="form-control form-control-user" id="replyer">
                                    </div>

                                    <div class="form-group">
                                        <label for="title">댓글</label>
                                        <input type="text" name="reply" class="form-control form-control-user" id="reply">
                                    </div>
                                    <hr>
                                    <div class="input-group-append">
                                        <button class="btn btn-primary operBtn" type="button">
                                            작성
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>


<form id="actionForm" action="/qna/list" method="get">
    <input type="hidden" name="page" value="${pageRequestDTO.page}">
    <input type="hidden" name="size" value="${pageRequestDTO.size}">

    <c:if test="${pageRequestDTO.type != null}">
        <input type="hidden" name="type" value="${pageRequestDTO.type}">
        <input type="hidden" name="keyword" value="${pageRequestDTO.keyword}">
    </c:if>
</form>
<%@ include file="../includes/footer.jsp" %>

<script>
    const actionForm = document.querySelector("#actionForm")
    const form = document.querySelector("#form1")

    document.querySelector(".btnList").addEventListener("click", ()=>{actionForm.submit()},false)

    document.querySelector(".btnDel").addEventListener("click", (e)=>{
        e.preventDefault()
        e.stopPropagation()

        form.setAttribute("action","/qna/remove")
        form.setAttribute("method","post")
        form.submit()

    },false)

</script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="/resources/js/reply.js"></script>
<script>

    function getList() {

        const target = document.querySelector(".reply")
        const qno = '${qnaDTO.qno}'

        function convertTemp(replyObj) {

            const {rno, qno, reply, replyer, replyDate, modDate} = {...replyObj}

            const temp = `<div class="card mb-4 py-2 border-left-primary shadow">
                          <div class="col-auto " align="right">
                          <a href="javascript:modReply(\${rno})" class="fas fa-tools fa-1x text-gray-300 m-1"></a>
                          <a href="javascript:delReply(\${rno})" class="fas fa-trash fa-1x text-gray-300 m-1" data-rno="\${rno}"></a></div>
                          <div class="ml-4 mb-3">
                          <div class="row no-gutters align-items-start">
                          <div class="col mr-2">
                          <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                          \${rno}--\${replyer}</div>
                          <div class="mod">
                          <div class="h5 mb-0 font-weight-bold text-gray-800" data-rno='\${rno}' data-replyer='\${replyer}'>
                          \${reply}</div>
                          <div class="text-xs font-weight-light text-secondary text-uppercase mb-1">
                          \${replyDate}</div>
                          </div>
                          </div></div></div></div>`

            return temp

        }

        getReplyList(qno).then(data => {

            console.log(data)
            let str = ""

            data.forEach(reply => {
                str += convertTemp(reply)
            })
            target.innerHTML = str

        })
    }

    (function () {
        getList()
    })()

    const replyDiv = document.querySelector("#reply-write")

    let oper = null

    document.querySelector(".operBtn").addEventListener("click", function () {

        oper = 'add'

        const qno = '${qnaDTO.qno}'
        const replyer = document.querySelector("input[name='replyer']").value
        const reply = document.querySelector("input[name='reply']").value

        if (oper === 'add') {

            const replyObj = {qno, replyer, reply}
            console.log(replyObj)

            addReply(replyObj).then(result => {
                getList()
                document.querySelector("input[name='replyer']").value = ""
                document.querySelector("input[name='reply']").value = ""
            })
        }

    }, false)



</script>

</body>

</html>