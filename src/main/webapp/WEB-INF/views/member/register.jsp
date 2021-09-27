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
                            <h1 class="h4 text-gray-900 mb-4">NORI REGISTER</h1>
                        </div>
                        <form id="form1" action="/member/register" method="post">
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
                            <div class="form-group">
                            <button type="submit" id="submitBtn" class="btn btn-primary btn-user btn-block">
                                Nori Register Account
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

<script>
    document.querySelector("#submitBtn").addEventListener("click", (e)=> {
        e.stopPropagation()
        e.preventDefault()
        const form1 = document.querySelector("#form1")
        form1.submit()
    }, false)

</script>
</body>
</html>