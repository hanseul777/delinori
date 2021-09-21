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
                                <button type="button" class="btn btn-info btnGoList">LIST</button>
                                <button type="button" class="btn btn-info btnDel">DELETE</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<form id="actionForm" action="/board/list" method="get">
    <input type="hidden" name="page" value="${pageRequestDTO.page}">
    <input type="hidden" name="size" value="${pageRequestDTO.size}">
</form>
<%@ include file="../includes/footer.jsp" %>

<script>
    const actionForm = document.querySelector("#actionForm")
    const form = document.querySelector("#form1")

    document.querySelector(".btnGoList").addEventListener("click", ()=>{actionForm.submit()},false)

    document.querySelector(".btnDel").addEventListener("click", (e)=>{
        e.preventDefault()
        e.stopPropagation()

        form.setAttribute("action","/board/remove")
        form.setAttribute("method","post")
        form.submit()

    },false)

</script>

</body>

</html>