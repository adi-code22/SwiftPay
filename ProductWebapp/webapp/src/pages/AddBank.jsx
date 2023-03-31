import React, { useEffect, useState } from "react";
import Sidenav from "../components/Sidenav";
import Navbar from "../components/Navbar";
import "../styles/Dashboard.css";
import "../styles/Bankdetails.css";
import TextField from "@mui/material/TextField";
import MenuItem from "@mui/material/MenuItem";
import {
  Button,
  Card,
  CardContent,
  Grid,
  Typography,
  Box,
  Divider,
} from "@mui/material";
import axios from "axios";

const url = " https://2430-171-78-178-71.in.ngrok.io/bank-service/bank/add";

const banks = [
  {
    value: "State Bank of India",
  },
  {
    value: "Bank of America ",
  },
  {
    value: "ICICI",
  },
  {
    value: "Axis Bank",
  },
];

export default function AddBank() {
  const [data, SetData] = useState([]);
  const handleChange = (event) => {
    let value = event.target.value;
    SetData(({ ...data }[value] = event.target.name));
    console.log(event.target.name + "------" + value);
  };
  const handleSubmit = () => {
    axios
      .get("https://2430-171-78-178-71.in.ngrok.io/bank-service/bank/get")
      .then((response) => {
        console.log("get method");
        console.log(response.data);
      });
    return axios.post(url).then((response) => {
      console.log("post Method");
      console.log(response.data);
    });
  };

  useEffect(() => {
    handleSubmit();
  }, []);

  return (
    <>
      <div className="bg-color">
        <Navbar />
        <Box height={30} />
        <Box sx={{ display: "flex" }}>
          <Sidenav />
          <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
            <section style={{ padding: "4vh" }}>
              <Grid container direction="row" spacing={3} className="gridbank">
                <Grid item xs="12">
                  <Card className="gridbank">
                    <CardContent>
                      <Grid container>
                        <Grid container sx={{ margin: "10px" }}>
                          <Grid item xs="6">
                            <Typography sx={{ fontSize: 18, paddingTop: 2 }}>
                              Bank Name
                            </Typography>
                          </Grid>

                          <Grid item xs="6">
                            <TextField
                              id="outlined-select-currency"
                              select
                              label="Select"
                              onChange={handleChange}
                              name="bankName"
                              required
                              helperText="Please select your bank"
                              fullWidth
                            >
                              {banks.map((option) => (
                                <MenuItem
                                  key={option.value}
                                  value={option.value}
                                >
                                  {option.value}
                                </MenuItem>
                              ))}
                            </TextField>
                          </Grid>
                        </Grid>
                        <Divider />{" "}
                        <Grid container sx={{ margin: "10px" }}>
                          <Grid item xs="6">
                            <Typography sx={{ fontSize: 18, paddingTop: 2 }}>
                              Account Number
                            </Typography>
                          </Grid>

                          <Grid item xs="6">
                            <TextField
                              required
                              id="outlined-required"
                              label="Required"
                              name="accountNumber"
                              onChange={handleChange}
                              fullWidth
                            />
                          </Grid>
                        </Grid>
                        <Divider />{" "}
                      </Grid>
                      <Grid container>
                        <Grid container sx={{ margin: "10px" }}>
                          <Grid item xs="6">
                            <Typography sx={{ fontSize: 18, paddingTop: 2 }}>
                              Bank Branch
                            </Typography>
                          </Grid>

                          <Grid item xs="6">
                            <TextField
                              required
                              name="bankBranch"
                              id="outlined-required"
                              label="Required"
                              onChange={handleChange}
                              fullWidth
                            />
                          </Grid>
                        </Grid>
                        <Divider />{" "}
                        <Grid container sx={{ margin: "10px" }}>
                          <Grid item xs="6">
                            <Typography sx={{ fontSize: 18, paddingTop: 2 }}>
                              Account Type
                            </Typography>
                          </Grid>

                          <Grid item xs="6">
                            <TextField
                              required
                              id="outlined-required"
                              label="Required"
                              name="accountType"
                              onChange={handleChange}
                              fullWidth
                            />
                          </Grid>
                        </Grid>
                        <Divider />{" "}
                        <Grid container sx={{ margin: "10px" }}>
                          <Grid item xs="6">
                            <Typography sx={{ fontSize: 18, paddingTop: 2 }}>
                              SWIFT Code
                            </Typography>
                          </Grid>

                          <Grid item xs="6">
                            <TextField
                              required
                              id="outlined-required"
                              label="Required"
                              name="bankSwiftCode"
                              onChange={handleChange}
                              fullWidth
                            />
                          </Grid>
                        </Grid>
                        <Grid container sx={{ margin: "10px" }}>
                          <Grid item xs="6">
                            <Typography sx={{ fontSize: 18, paddingTop: 2 }}>
                              Balance
                            </Typography>
                          </Grid>

                          <Grid item xs="6">
                            <TextField
                              required
                              id="outlined-required"
                              label="Required"
                              name="balance"
                              fullWidth
                              onChange={handleChange}
                              sx={{ paddingBottom: 6 }}
                            />
                          </Grid>
                          <Grid />
                          <Divider />

                          <Grid item xs={12}>
                            <Box textAlign="center">
                              <Button
                                onClick={handleSubmit}
                                variant="contained"
                                style={{ backgroundColor: "#005555" }}
                                type="submit"
                              >
                                Add Account
                              </Button>
                            </Box>
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
