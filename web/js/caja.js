// Inicializacion por defecto: inicio de sesion
window.onload = function() {
    mostrarCamposInicioSesion();
}

// Funcion que muestra los campos de inicio de sesion
function mostrarCamposInicioSesion() {
    document.getElementById('accion').value = 'login';
    document.getElementById('btnInicioSesion').disabled = true;
    document.getElementById('btnRegistro').disabled = false;
    document.getElementById('form-tarjeta').style.display = 'none';
    document.getElementById('tipo').removeAttribute('required');
    document.getElementById('numero').removeAttribute('required');
}

// Funcion que muestra los campos de registro
function mostrarCamposRegistro() {
    document.getElementById('accion').value = 'registrar';
    document.getElementById('btnInicioSesion').disabled = false;
    document.getElementById('btnRegistro').disabled = true;
    document.getElementById('form-tarjeta').style.display = 'block';
    document.getElementById('tipo').setAttribute('required', 'required');
    document.getElementById('numero').setAttribute('required', 'required');
}
