import React, { useEffect, useState } from "react";
import Sidenav from "../components/Sidenav";
import Navbar from "../components/Navbar";
import "../styles/Bankdetails.css";
import {
  Card,
  CardContent,
  CardHeader,
  Grid,
  Typography,
  Box,
  Divider,
  TextField,
} from "@mui/material";
import axios from "axios";
import { Details } from "@mui/icons-material";

export default function BankDetails() {
  const [accountDetails, setAccountDetails] = useState();
  let token = localStorage.getItem("token");

  const fetchDetails = () => {
    axios
      .get("https://swiftpay.stackroute.io/bank-service/account/get", {
        headers: {
          "Content-Type": "application/json",
          token: token,
        },
      })
      .then((response) => {
        console.log(response.data);
        setAccountDetails(response.data);
      });
  };

  useEffect(() => {
    fetchDetails();
  }, []);
  if (!accountDetails)
    return (
      <>
        <div className="bg-color">
          {console.log(accountDetails)}
          <Navbar />
          <Box height={30} />
          <Box sx={{ display: "flex" }}>
            <Sidenav />
            <Box
              component="main"
              sx={{ flexGrow: 0.5, p: 3, marginLeft: 20 }}
            ></Box>
          </Box>
        </div>
      </>
    );
  else
    return (
      <>
        <div className="bg-color">
          {console.log(accountDetails)}
          <Navbar />
          <Box height={30} />
          <Box sx={{ display: "flex" }}>
            <Sidenav />
            <Box
              component="main"
              sx={{ flexGrow: 0.5, p: 4.5, marginLeft: 20 }}
            >
              <section className="bankdetails">
                <Grid
                  container
                  direction="row"
                  spacing={3}
                  className="gridbank"
                >
                  <Grid item xs="12">
                    <Card className="gridbank">
                      <CardHeader
                        title={
                          <Typography
                            gutterBottom
                            variant="h5"
                            component="h1"
                            sx={{
                              textAlign: "center",
                              color: "#005555",
                              fontSize: 30,
                              fontWeight: "bold",
                            }}
                          >
                            My bank
                          </Typography>
                        }
                      />
                      <CardContent>
                        <Grid container>
                          <Grid
                            container
                            sx={{
                              margin: "10px",
                              textAlign: "center",
                              textAlign: "center",
                            }}
                          >
                            <Grid item xs="6">
                              <Typography
                                sx={{
                                  fontSize: 20,
                                  paddingTop: 2,
                                  fontWeight: "bold",
                                  textAlign: "left",
                                }}
                              >
                                Bank Name
                              </Typography>
                            </Grid>

                            <Grid item xs="6">
                              {/* <Typography
                                gutterBottom
                                variant="h5"
                                component="h1"
                                sx={{
                                  fontSize: 18,
                                  paddingTop: 2,
                                  color: "black",
                                }}
                              >
                                {accountDetails.bankModel.bankName}
                              </Typography> */}

                              <TextField
                                id="outlined-read-only-input"
                                defaultValue={accountDetails.bankModel.bankName}
                                InputProps={{
                                  readOnly: true,
                                }}
                                fullWidth
                              />
                            </Grid>
                          </Grid>
                          <Divider />{" "}
                          <Grid
                            container
                            sx={{ margin: "10px", textAlign: "center" }}
                          >
                            <Grid item xs="6">
                              <Typography
                                sx={{
                                  fontSize: 20,
                                  paddingTop: 2,
                                  fontWeight: "bold",
                                  textAlign: "left",
                                }}
                              >
                                Account Number
                              </Typography>
                            </Grid>

                            <Grid item xs="6">
                              {/* <Typography
                                gutterBottom
                                variant="h5"
                                component="h1"
                                sx={{
                                  fontSize: 18,
                                  paddingTop: 2,
                                  color: "black",
                                }}
                              >
                                {accountDetails.accountNumber}
                              </Typography> */}

                              <TextField
                                id="outlined-read-only-input"
                                defaultValue={accountDetails.accountNumber}
                                InputProps={{
                                  readOnly: true,
                                }}
                                fullWidth
                              />
                            </Grid>
                          </Grid>
                          <Divider />{" "}
                        </Grid>
                        <Grid container>
                          <Grid
                            container
                            sx={{ margin: "10px", textAlign: "center" }}
                          >
                            <Grid item xs="6">
                              <Typography
                                sx={{
                                  fontSize: 20,
                                  paddingTop: 2,
                                  fontWeight: "bold",
                                  textAlign: "left",
                                }}
                              >
                                Bank Branch
                              </Typography>
                            </Grid>

                            <Grid item xs="6">
                              {/* <Typography
                                gutterBottom
                                variant="h5"
                                component="h1"
                                sx={{
                                  fontSize: 18,
                                  paddingTop: 2,
                                  color: "black",
                                }}
                              >
                                {accountDetails.bankBranch}
                              </Typography> */}

                              <TextField
                                id="outlined-read-only-input"
                                defaultValue={accountDetails.bankBranch}
                                InputProps={{
                                  readOnly: true,
                                }}
                                fullWidth
                              />
                            </Grid>
                          </Grid>
                          <Divider />{" "}
                          <Grid
                            container
                            sx={{ margin: "10px", textAlign: "center" }}
                          >
                            <Grid item xs="6">
                              <Typography
                                sx={{
                                  fontSize: 20,
                                  paddingTop: 2,
                                  fontWeight: "bold",
                                  textAlign: "left",
                                }}
                              >
                                Account Type
                              </Typography>
                            </Grid>

                            <Grid item xs="6">
                              {/* <Typography
                                gutterBottom
                                variant="h5"
                                component="h1"
                                sx={{
                                  fontSize: 18,
                                  paddingTop: 2,
                                  color: "black",
                                }}
                              >
                                {accountDetails.accountType}
                              </Typography> */}

                              <TextField
                                id="outlined-read-only-input"
                                defaultValue={accountDetails.accountType}
                                InputProps={{
                                  readOnly: true,
                                }}
                                fullWidth
                              />
                            </Grid>
                          </Grid>
                          <Divider />{" "}
                          <Grid
                            container
                            sx={{ margin: "10px", textAlign: "center" }}
                          >
                            <Grid item xs="6">
                              <Typography
                                sx={{
                                  fontSize: 20,
                                  paddingTop: 2,
                                  fontWeight: "bold",
                                  textAlign: "left",
                                }}
                              >
                                SWIFT Code
                              </Typography>
                            </Grid>

                            <Grid item xs="6">
                              {/* <Typography
                                gutterBottom
                                variant="h5"
                                component="h1"
                                sx={{
                                  fontSize: 18,
                                  paddingTop: 2,
                                  color: "black",
                                }}
                              >
                                {accountDetails.bankModel.bankSwiftCode}
                              </Typography> */}

                              <TextField
                                id="outlined-read-only-input"
                                defaultValue={
                                  accountDetails.bankModel.bankSwiftCode
                                }
                                InputProps={{
                                  readOnly: true,
                                }}
                                fullWidth
                              />
                            </Grid>
                          </Grid>
                          <Grid
                            container
                            sx={{ margin: "10px", textAlign: "center" }}
                          >
                            <Grid item xs="6">
                              <Typography
                                sx={{
                                  fontSize: 20,
                                  paddingTop: 2,
                                  fontWeight: "bold",
                                  textAlign: "left",
                                }}
                              >
                                Balance
                              </Typography>
                            </Grid>

                            <Grid item xs="6">
                              {/* <Typography
                                gutterBottom
                                variant="h5"
                                component="h1"
                                sx={{
                                  fontSize: 18,
                                  paddingTop: 2,
                                  color: "black",
                                }}
                              >
                                {accountDetails.balance}
                              </Typography> */}

                              <TextField
                                id="outlined-read-only-input"
                                defaultValue={accountDetails.balance}
                                InputProps={{
                                  readOnly: true,
                                }}
                                fullWidth
                              />
                            </Grid>
                          </Grid>
                        </Grid>
                      </CardContent>
                    </Card>
                  </Grid>
                </Grid>
              </section>
            </Box>
          </Box>
        </div>
      </>
    );
}
