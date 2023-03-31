import { Box } from "@mui/material";
import React from "react";
import Sidenav from "../components/Sidenav";
import Navbar from "../components/Navbar";
import "../styles/transactionHistory.css";
import axios from "axios";
import { DataGrid, GridToolbar } from "@mui/x-data-grid";
const url = "https://swiftpay.stackroute.io/transaction-service/transactions/get";

export default function Transactions() {
  // const genData = require("../generated.json");
  // let genRows = genData.transactions;

  let token = localStorage.getItem("token");

  const accountNumber = () => {
    return new Promise(function (resolve, reject) {
      axios
        .get("https://swiftpay.stackroute.io/bank-service/account/get", {
          headers: {
            "Content-Type": "application/json",
            token: token,
          },
        })
        .then(
          (response) => {
            resolve(response.data.accountNumber);
          },
          (error) => {
            reject(error);
          }
        );
    });
  };

  const columns = [
    {
      field: "transactionId",
      headerName: "Transaction Id",
      headerAlign: "center",
      width: 120,
    },
    {
      field: "time",
      headerName: "Time Stamp",
      headerAlign: "center",
      flex: 1,
    },
    {
      field: "message",
      headerName: " Description",
      headerAlign: "center",
      width: 300,
    },
    {
      field: "receiverAccountNumber",
      headerName: "To Account Number",
      headerAlign: "center",
      flex: 1,
    },
    {
      field: "receiverName",
      headerName: "Beneficiary Name",
      headerAlign: "center",
      flex: 1,
    },
    {
      field: "senderAccountNumber",
      headerName: "From Account Number",
      headerAlign: "center",
      flex: 1,
    },

    {
      field: "credit",
      headerName: "Credit",
      headerAlign: "center",
    },
    {
      field: "debit",
      headerName: "Debit",
      headerAlign: "center",
    },
    {
      field: "status",
      headerName: "Status",
      headerAlign: "center",
      flex: 0,
    },
  ];

  const [rows, setRows] = React.useState([]);

  React.useEffect(() => {
    axios
      .get(url, {
        headers: {
          "Content-Type": "application/json",
          token: token,
        },
      })
      .then((res) => {
        return res.data;
      })
      .then((response) => {
        accountNumber().then((result) => {
          console.log(result);
          for (let index = 0; index < response.length; index++) {
            if (response[index].senderAccountNumber != result) {
              response[index].credit = response[index].debit;
              response[index].debit = 0;
            }
          }
          setRows(response);
        });
      });
  }, []);

  console.log(rows);

  return (
    <>
      <div>
        {/* {console.log(genData)} */}
        <Navbar />
        <Box height={30} />
        <Box sx={{ display: "flex" }}>
          <Sidenav />
          <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
            <div className="transactionDiv">
              <h1>Transaction History</h1>
              <Box
                sx={{ height: " 82vh", width: "100%", alignItems: "center" }}
              >
                <DataGrid
                  sx={{
                    m: 2,
                    overflowX: "scroll",
                  }}
                  disableRowSelectionOnClick
                  autoHeight
                  rows={rows}
                  // rows={genRows}
                  columns={columns}
                  getRowId={(row) =>
                    row.transactionId +
                    row.time +
                    row.message +
                    row.receiverAccountNumber +
                    row.receiverName +
                    row.senderAccountNumber +
                    row.credit +
                    row.debit +
                    row.status
                  }
                  initialState={{
                    pagination: {
                      paginationModel: {
                        pageSize: 12,
                      },
                    },
                  }}
                  pageSizeOptions={[25, 30, 35, 40]}
                  slots={{
                    toolbar: GridToolbar,
                  }}
                />
              </Box>
            </div>
          </Box>
        </Box>
      </div>
    </>
  );
}
