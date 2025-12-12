<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Send Mail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #e9ecef; padding: 40px; }
        .mail-box { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); max-width: 700px; margin: auto; }
    </style>
</head>
<body>

<div class="mail-box">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-primary m-0">Email</h2>
        <a href="/lab5" class="btn btn-sm btn-outline-secondary">Home</a>
    </div>

    <c:if test="${not empty message}">
        <div class="alert alert-info">${message}</div>
    </c:if>

    <form action="/lab5/mail" method="post" enctype="multipart/form-data">
        
        <div class="row mb-3">
            <div class="col-md-6">
                 <label class="form-label fw-bold">From:</label>
                 <input name="from" type="text" class="form-control" value="tmngoc732@gmail.com" readonly>
            </div>
            <div class="col-md-6">
                 <label class="form-label fw-bold">To:</label>
                 <input name="to" type="text" class="form-control" placeholder="Recipient's email">
            </div>
        </div>

        <div class="mb-3">
            <label class="form-label fw-bold">Subject:</label>
            <input name="subject" type="text" class="form-control" placeholder="Email subject">
        </div>

        <div class="mb-3">
            <label class="form-label fw-bold">Message:</label>
            <textarea name="body" class="form-control" rows="5" placeholder="Write your message here..."></textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Attachment:</label>
            <input type="file" name="photo_file" class="form-control">
        </div>

        <button type="submit" class="btn btn-primary w-100">Send Email 🚀</button>

    </form>
</div>

</body>
</html>