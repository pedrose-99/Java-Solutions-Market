import './App.css'
import ClientListComponent from './screens/ClientListComponent'
import FooterComponent from './components/FooterComponent'
import HeaderComponent from './components/HeaderComponent'
import {BrowserRouter, Route, Routes} from 'react-router-dom'
import ClientComponent from './components/ClientComponent'
import EmployeeComponent from './components/EmployeeComponent'
import EmployeeListComponent from './screens/EmployeeListComponent'
import HomePage from './screens/HomePage'

function App() {


  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
        <Routes>
          {/* //http:localhost:5173 */}
          <Route path= '/' element = { <HomePage />}></Route>

          {/* //http:localhost:5173/clients */}
          <Route path= '/clients' element = {<ClientListComponent/>}></Route>
          {/* //http:localhost:5173/add-client */}
          <Route path='/add-client' element = {<ClientComponent/>}></Route>
          {/* //http:localhost:5173/update-client/id*/}
          <Route path='/update-client/:client_id' element = {<ClientComponent/>}></Route>
        
          {/* //http:localhost:5173/employees */}
          <Route path= '/employees' element = {<EmployeeListComponent/>}></Route>
          {/* //http:localhost:5173/add-employee */}
          <Route path='/add-employee' element = {<EmployeeComponent/>}></Route>
          {/* //http:localhost:5173/update-employee/id*/}
          <Route path='/update-employee/:employee_id' element = {<EmployeeComponent/>}></Route>
        </Routes>
        {/* <FooterComponent /> */}
      </BrowserRouter>
    </>
  )
}

export default App
