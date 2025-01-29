const API_URL = "/api/productos";

// Obtener todos los productos y mostrarlos en una tabla
function getAllProducts() {
    fetch(API_URL)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#productTable tbody");
            tableBody.innerHTML = ""; // Limpiar tabla antes de cargar datos

            data.forEach(producto => {
                let row = tableBody.insertRow();
                row.insertCell(0).innerText = producto.idProducto;
                row.insertCell(1).innerText = producto.nombre;
                row.insertCell(2).innerText = producto.descripcion;
                row.insertCell(3).innerText = `$${producto.precio.toLocaleString('es-ES', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}`;
                row.insertCell(4).innerText = producto.fechaCreacion;
            });
        })
        .catch(error => console.error("Error al obtener productos:", error));
}

// Agregar un producto
document.getElementById("addProductForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const producto = {
        nombre: document.getElementById("nombre").value,
        descripcion: document.getElementById("descripcion").value,
        precio: parseFloat(document.getElementById("precio").value),
        fechaCreacion: document.getElementById("fechaCreacion").value
    };

    fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(producto)
    })
        .then(response => response.json())
        .then(data => {
            alert("Producto agregado con éxito!");
            getAllProducts(); // Recargar la tabla automáticamente
        })
        .catch(error => console.error("Error al agregar producto:", error));
});

// Obtener un producto por ID
function getProductById() {
    const id = document.getElementById("buscarId").value;

    fetch(`${API_URL}/${id}`)
        .then(response => {
            if (!response.ok) throw new Error("Producto no encontrado");
            return response.json();
        })
        .then(data => {
            document.getElementById("productoEncontrado").innerText = `ID: ${data.idProducto} - ${data.nombre} - $${data.precio}`;
        })
        .catch(error => {
            document.getElementById("productoEncontrado").innerText = "Producto no encontrado.";
        });
}

// Eliminar un producto
function deleteProduct() {
    const id = document.getElementById("deleteId").value;

    fetch(`${API_URL}/${id}`, { method: "DELETE" })
        .then(response => {
            if (!response.ok) throw new Error("Producto no encontrado");
            alert("Producto eliminado correctamente!");
            getAllProducts(); // Actualizar la tabla después de eliminar
        })
        .catch(error => console.error("Error al eliminar producto:", error));
}
