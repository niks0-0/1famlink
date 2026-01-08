<?php
header('Content-Type: application/json');

include "db.php";

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    echo json_encode([
        "status" => "error",
        "message" => "Invalid request method"
    ]);
    exit;
}

$user_id = $_POST['user_id'] ?? '';

if (empty($user_id)) {
    echo json_encode([
        "status" => "error",
        "message" => "User ID missing"
    ]);
    exit;
}

$family_code = strtoupper(substr(md5(time()), 0, 6));

$query = "INSERT INTO families (family_code, admin_id)
          VALUES ('$family_code', '$user_id')";

if (mysqli_query($conn, $query)) {
    echo json_encode([
        "status" => "success",
        "family_code" => $family_code
    ]);
} else {
    echo json_encode([
        "status" => "error",
        "message" => mysqli_error($conn)
    ]);
}
