<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Staff</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; padding: 30px; }
        .form-container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 15px rgba(0,0,0,0.1); max-width: 600px; margin: auto; }
    </style>
</head>
<body>

<div class="form-container">
    <h2 class="text-center mb-4 text-success">Staff Information</h2>
    
    <form action="/lab5/add" method="post" enctype="multipart/form-data">

        <div class="mb-3">
            <label class="form-label fw-bold">Fullname</label>
            <input name="fullname" type="text" class="form-control" placeholder="Nguyen Van A" required />
        </div>

        <div class="mb-3">
            <label class="form-label fw-bold">Avatar</label>
            <input type="file" name="photo_file" class="form-control" />
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label class="form-label fw-bold">Birthday</label>
                <input name="birthday" type="date" class="form-control" />
            </div>

            <div class="col-md-6 mb-3">
                <label class="form-label fw-bold">Country</label>
                <select name="country" class="form-select">
                    <option selected disabled>Select country...</option>
                    <option value="Vietnamese">Vietnamese</option>
                    <option value="United States">United States</option>
                    <option value="United Kingdom">United Kingdom</option>
                </select>
            </div>
        </div>

        <div class="mb-3">
            <label class="form-label fw-bold d-block">Gender</label>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="gender" value="True" checked>
                <label class="form-check-label">Male</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="gender" value="False">
                <label class="form-check-label">Female</label>
            </div>
        </div>

        <div class="mb-3 form-check">
            <input type="checkbox" name="married" class="form-check-input" id="chkMarried" />
            <label class="form-check-label" for="chkMarried">Is Married?</label>
        </div>

        <div class="mb-3">
            <label class="form-label fw-bold d-block">Hobbies</label>
            <div class="form-check form-check-inline">
                <input type="checkbox" name="hobbies" value="Coding" class="form-check-input" /> Coding
            </div>
            <div class="form-check form-check-inline">
                <input type="checkbox" name="hobbies" value="Travel" class="form-check-input" /> Travel
            </div>
            <div class="form-check form-check-inline">
                <input type="checkbox" name="hobbies" value="Music" class="form-check-input" /> Music
            </div>
            <div class="form-check form-check-inline">
                <input type="checkbox" name="hobbies" value="Other" class="form-check-input" /> Other
            </div>
        </div>

        <div class="mb-3">
            <label class="form-label fw-bold">Notes</label>
            <textarea name="note" class="form-control" rows="3"></textarea>
        </div>

        <div class="d-grid gap-2">
            <button type="submit" class="btn btn-success btn-lg">Add Staff</button>
            <a href="/lab5" class="btn btn-secondary">Cancel</a>
        </div>

    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>