import React from 'react'

const FooterComponent = () => {
  return (
    <div>
        <footer className="footer">
            <div className="footer-container">
                <div className="footer-section">
                <h4><a  className="title" href='/'>CRM App</a></h4>
                <p>Gestión eficiente de clientes, productos y empleados.</p>
                </div>
                <div className="footer-section">
                <h4>Secciones</h4>
                <ul>
                    <li><a href="/clients">Clientes</a></li>
                    <li><a href="/products">Productos</a></li>
                    <li><a href="/employees">Empleados</a></li>
                    <li><a href="/providers">Proovedores</a></li>
                    <li><a href="/inventory">Inventario</a></li>
                    <li><a href="/sales">Ventas</a></li>
                    <li><a href="/buys">Compras</a></li>
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