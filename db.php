<?php
error_reporting(0);
header('Content-Type: application/json');

$host = "localhost";
$user = "root";
$password = "";
$database = "1famlink_db";

$conn = new mysqli($host, $user, $password, $database);

if ($conn->connect_error) {
    echo json_encode([
        "status" => "error",
        "message" => "Database connection failed"
    ]);
    exit;
}

$conn->set_charset("utf8mb4");
