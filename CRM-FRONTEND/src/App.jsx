import './App.css'
import ClientListComponent from './screens/ClientListComponent'
import FooterComponent from './components/FooterComponent'
import HeaderComponent from './components/HeaderComponent'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import ClientComponent from './components/ClientComponent'
import EmployeeComponent from './components/EmployeeComponent'
import EmployeeListComponent from './screens/EmployeeListComponent'
import HomePage from './screens/HomePage'
import InventoryListComponent from './screens/InventoryListComponent'
import InventoryComponent from './components/InventoryComponent'
import SalesComponent from './components/SalesComponent'
import SalesListComponent from './screens/SalesListComponent'

function App() {
  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
        <Routes>
          <Route path='/' element={<HomePage />} />

          <Route path='/clients' element={<ClientListComponent />} />
          <Route path='/add-client' element={<ClientComponent />} />
          <Route path='/update-client/:client_id' element={<ClientComponent />} />

          <Route path='/employees' element={<EmployeeListComponent />} />
          <Route path='/add-employee' element={<EmployeeComponent />} />
          <Route path='/update-employee/:employee_id' element={<EmployeeComponent />} />

          <Route path='/inventory' element={<InventoryListComponent />} />
          <Route path='/add-inventory-product' element={<InventoryComponent />} />
          <Route path='/update-inventory-product/:product_id' element={<InventoryComponent />} />

          <Route path='/add-sale' element={<SalesComponent />} /> {/* NUEVA RUTA */}
          <Route path='/sales' element={<SalesListComponent/>}/>
        </Routes>
        {/* <FooterComponent /> */}
      </BrowserRouter>
    </>
  )
}

export default App
