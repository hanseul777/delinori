<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--헤더 붙여넣기( 앞으로 이거 긁어 쓰세요 ) -->
<%@ include file="../includes/header.jsp" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">문의사항</h1>
        <a href="/board/register" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">글쓰기</a>
    </div>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">문의사항</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>QNO</th>
                        <th>TITLE</th>
                        <th>WRITER</th>
                        <th>REGDATE</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${dtoList}" var="dto">
                        <tr>
                            <td><c:out value="${dto.qno}"></c:out></td>
                            <td><a href="javascript:moveRead(${dto.qno})"><c:out value="${dto.title}"></c:out></a></td>
                            <td><c:out value="${dto.writer}"></c:out></td>
                            <td><c:out value="${dto.regDate}"></c:out></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>



    </div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
<form id="actionForm" action="/board/list" method="get">
    <input type="hidden" name="page" value="${pageMaker.page}">
    <input type="hidden" name="size" value="${pageMaker.size}">
</form>


<!--푸터 붙여넣기( 앞으로 이거 긁어 쓰세요 ) -->
<%@ include file="../includes/footer.jsp" %>

<script>
    const actionForm = document.querySelector("#actionForm")

    function moveRead(qno){
        actionForm.setAttribute("action","/board/read")
        actionForm.innerHTML += `<input type='hidden' name='qno' value='\${qno}'>`

        actionForm.submit()
    }

</script>

</body>

</html>