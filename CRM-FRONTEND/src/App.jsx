import './App.css'
import ClientListComponent from './screens/ClientListComponent'
import FooterComponent from './components/FooterComponent'
import HeaderComponent from './components/HeaderComponent'
import {BrowserRouter, Route, Routes} from 'react-router-dom'
import ClientComponent from './components/ClientComponent'

function App() {


  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
        <Routes>
          {/* //http:localhost:5173 */}
          <Route path= '/' element = { <ClientListComponent />}></Route>
          {/* //http:localhost:5173/clients */}
          <Route path= '/clients' element = {<ClientListComponent/>}></Route>
          {/* //http:localhost:5173/add-client */}
          <Route path='/add-client' element = {<ClientComponent/>}></Route>
          {/* //http:localhost:5173/update-client/id*/}
          <Route path='/update-client/:client_id' element = {<ClientComponent/>}></Route>

        </Routes>
        {/* <FooterComponent /> */}
      </BrowserRouter>
    </>
  )
}

export default App
