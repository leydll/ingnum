<?php
// Récupérer le chemin de la requête
$requestUri = $_SERVER['REQUEST_URI'];
$requestMethod = $_SERVER['REQUEST_METHOD'];

// Route pour /customer/{name}
if (preg_match('#^/customer/([^/]+)$#', $requestUri, $matches)) {
    $name = urldecode($matches[1]);
    header('Content-Type: application/json');
    echo json_encode(['name' => $name]);
    exit;
}

// Route par défaut
if ($requestUri === '/' || $requestUri === '/index.php') {
    echo "leïla";
    exit;
}

// Route non trouvée
http_response_code(404);
echo json_encode(['error' => 'Not found']);
?>