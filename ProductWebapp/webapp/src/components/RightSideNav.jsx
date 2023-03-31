import React from 'react'
import logo from '../images/logo.png'

import mystyle from '../styles/RightnavBar.css'
import AttachMoneyOutlinedIcon from '@mui/icons-material/AttachMoneyOutlined';
const RightSideNav = () => {
  return (
    <div style={{
      position: 'fixed',
      top: 100,
      right: 0,
      width: '35%',
      height: "100%",
      color: "black",
      padding: '20px',

      backgroundColor: '#eceff1',
    }}>
      <center>
        <img
          src={logo}
          alt="Swift Icon"
          style={{
            display: 'block',
            margin: '0 auto',
            width: '40%',
            height: '40%',
            float: "left",
            marginTop: "40px"
          }}
        />
        <h1 style={{ float: "left", color: "black", marginTop: "100px", marginLeft: "10px", fontSize: "50px" }}>SwiftPay</h1>
        <br />

        <p style={{ fontFamily: "fantasy", fontSize: "30px", marginTop: "190px", marginRight: "65px", textAlign: "justify", lineHeight: 2, backgroundImage: `url(${AttachMoneyOutlinedIcon})` }}>
          <b>SwiftPay</b> is an application that simulates the operations of a Bank with Cash & Non-Cash accounts. The simulator allows for setting up new accounts with initial balances (in different asset classes and currencies) and updating these balances in response to SWIFT messages.
        </p>
      </center>
    </div>
  )
}

export default RightSideNav
