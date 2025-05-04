import React from 'react'

const FooterComponent = () => {
  return (
    <div>
        <footer className="footer">
            <div className="footer-container">
                <div className="footer-section">
                <h4>CRM App</h4>
                <p>Gestión eficiente de clientes, productos y empleados.</p>
                </div>
                <div className="footer-section">
                <h4>Secciones</h4>
                <ul>
                    <li><a href="/clientes">Clientes</a></li>
                    <li><a href="/productos">Productos</a></li>
                    <li><a href="/empleados">Empleados</a></li>
                    <li><a href="/reportes">Reportes</a></li>
                </ul>
                </div>
                <div className="footer-section">
                <h4>Contacto</h4>
                <p>Email: soporte@crm.com</p>
                <p>Tel: +34 689987712</p>
                </div>
            </div>
            <div className="footer-bottom">
                <p>&copy; 2025 CRM App. Todos los derechos reservados.</p>
            </div>
        </footer>
    </div>
  )
}

export default FooterComponent