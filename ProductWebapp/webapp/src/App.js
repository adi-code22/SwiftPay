import { Routes, Route, BrowserRouter } from "react-router-dom";
import RightSideNav from "./components/RightSideNav";
import AddBank from "./pages/AddBank";
import BankDetails from "./pages/BankDetails";
import Login from "./pages/Login";
import SignUp from "./pages/SignUp";
import Transactions from "./pages/Transactions";
import Transfer from "./pages/Transfer";
import UserProfile from "./pages/UserProfile";
function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" exact element={<Login />}></Route>
          <Route path="/login" exact element={<Login />}></Route>
          <Route path="/signup" exact element={<SignUp />}></Route>
          <Route path="/profile" exact element={<UserProfile />}></Route>
          <Route path="/bank-details" exact element={<BankDetails />}></Route>
          <Route path="/transfer" exact element={<Transfer />}></Route>
          <Route path="/transactions" exact element={<Transactions />}></Route>
          <Route path="/addbank" exact element={<AddBank />}></Route>
        </Routes>
      </BrowserRouter>
      
    </>
  );
}

export default App;
