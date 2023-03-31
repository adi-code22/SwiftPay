import React, { useState, useEffect } from "react";
import Navbar from "../components/Navbar";
import Sidenav from "../components/Sidenav";
import "../styles/Userprofile.css";
import RightSideNav from "../components/RightSideNav";
import { MuiTelInput } from "mui-tel-input";
import {
  Button,
  Card,
  CardContent,
  CardMedia,
  Grid,
  Typography,
  Box,
  TextField,
} from "@mui/material";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";

export default function Userprofile() {
  const [data, SetData] = useState([]);
  const [isFocused, setIsFocused] = useState(false);
  const [mobileNumber, setMobileNumber] = useState("");
  const [location, setLocation] = useState("");
  const [pannumber, setPannumber] = useState("");
  const navigate = useNavigate();
  // let email = localStorage.getItem("email");
  let token = localStorage.getItem("token");
  useEffect(() => {
    axios
      .get(`https://swiftpay.stackroute.io/user-service/users/verify`, {
        headers: {
          "Content-Type": "application/json",
          token: token,
        },
      })
      .then((res) => {
        // console.log(res.data);
        SetData(res.data);
      })
      .catch(function (error) {
        console.log(error.response.data);
      });
  }, []);
  // console.log(data.emailId);

  let mapData = {
    emailId: data.emailId,
    password: data.password,
    nameOfTheUser: data.nameOfTheUser,
    mobileNumber: mobileNumber,
    location: location,
    panNumber: pannumber,
  };
  const update = () => {
    axios
      .put(`https://swiftpay.stackroute.io/user-service/users/${data.emailId}`, mapData)
      .then((res) => {
        // console.log(res.data);
        // console.log("----put method----");
        SetData(res.data);

        toast.success("User updated successfully");
      })
      .catch(function (error) {
        console.log(error.response.data);
      });
  };
  return (
    <div className="bg-color">
      <Navbar />
      <Box sx={{ display: "flex" }}>
        <Sidenav />
        <RightSideNav />
        <Box component="main" sx={{ flexGrow: 1, p: 5 }}>
          <section className="user">
            <Grid container direction="row" spacing={10}>
              <Grid item xs="6">
                <Card sx={{ padding: "6vh" }}>
                  <CardMedia
                    component="img"
                    image="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp"
                    alt="avatar"
                    sx={{
                      maxWidth: "170px",
                      margin: "0px auto",
                      paddingRight: "15px",
                      borderRadius: "100%",
                      borderColor: "grey.500 ",
                    }}
                  />
                  <CardContent>
                    <Grid container sx={{ margin: "1%", paddingRight: 5 }}>
                      <Grid item xs="9">
                        <Typography
                          variant="h5"
                          sx={{ fontFamily: "sans-serif" }}
                        >
                          Name
                        </Typography>
                      </Grid>
                      <Grid item xs="3">
                        <Typography
                          variant="h6"
                          sx={{ fontFamily: "sans-serif", textAlign: "left" }}
                        >
                          {data.nameOfTheUser}
                        </Typography>
                      </Grid>
                    </Grid>
                    <Grid container sx={{ margin: "1%", paddingRight: 5 }}>
                      <Grid item xs="9">
                        <Typography
                          variant="h5"
                          sx={{ fontFamily: "sans-serif" }}
                        >
                          Email
                        </Typography>
                      </Grid>
                      <Grid item xs="3">
                        <Typography
                          variant="h6"
                          sx={{ fontFamily: "sans-serif", textAlign: "left" }}
                        >
                          {data.emailId}
                        </Typography>

                        {/* <TextField
                          variant="standard"
                          sx={{ fontSize: 10 }}
                          id="outlined-read-only-input"
                          value={data.emailId}
                          defaultValue={data.emailId}
                          InputProps={{
                            readOnly: true,
                            disableUnderline: true,
                          }}
                          fullWidth
                        /> */}
                      </Grid>
                    </Grid>
                    <Grid container sx={{ margin: "1%", paddingRight: 5 }}>
                      <Grid item xs="9">
                        <Typography
                          variant="h5"
                          sx={{ fontFamily: "sans-serif" }}
                        >
                          Password
                        </Typography>
                      </Grid>
                      <Grid item xs="3">
                        <Typography
                          variant="h6"
                          sx={{ fontFamily: "sans-serif", textAlign: "left" }}
                        >
                          {data.password}
                        </Typography>
                      </Grid>
                    </Grid>
                    <Grid container sx={{ margin: "1%" }}>
                      <Grid item xs="8">
                        <Typography
                          variant="h5"
                          sx={{ fontFamily: "sans-serif" }}
                        >
                          Mobile Number
                        </Typography>
                      </Grid>
                      <Grid item xs="4">
                        {!isFocused ? (
                          <Typography
                            variant="h6"
                            value={mobileNumber}
                            sx={{
                              fontFamily: "sans-serif",
                              paddingLeft: 2,
                            }}
                          >
                            {data.mobileNumber}
                          </Typography>
                        ) : (
                          <TextField
                            autoFocus
                            variant="standard"
                            value={mobileNumber}
                            placeholder={data.mobileNumber}
                            sx={{
                              fontFamily: "sans-serif",
                            }}
                            onChange={(event) =>
                              setMobileNumber(event.target.value)
                            }
                            InputProps={{
                              style: {
                                fontSize: 20,
                                paddingLeft: 15,
                              },
                            }}
                          />
                        )}
                      </Grid>
                    </Grid>
                    <Grid container sx={{ margin: "1%" }}>
                      <Grid item xs="8">
                        <Typography
                          variant="h5"
                          sx={{ fontFamily: "sans-serif" }}
                        >
                          Location
                        </Typography>
                      </Grid>
                      <Grid item xs="4">
                        {!isFocused ? (
                          <Typography
                            variant="h6"
                            value={location}
                            sx={{
                              fontFamily: "sans-serif",
                              paddingLeft: 2,
                            }}
                          >
                            {data.location}
                          </Typography>
                        ) : (
                          <TextField
                            autoFocus
                            variant="standard"
                            value={location}
                            placeholder={data.location}
                            sx={{
                              fontFamily: "sans-serif",
                            }}
                            onChange={(event) =>
                              setLocation(event.target.value)
                            }
                            InputProps={{
                              style: {
                                fontSize: 20,
                                paddingLeft: 15,
                              },
                            }}
                          />
                        )}
                      </Grid>
                    </Grid>
                    <Grid container sx={{ margin: "1%" }}>
                      <Grid item xs="8">
                        <Typography
                          variant="h5"
                          value={pannumber}
                          sx={{ fontFamily: "sans-serif" }}
                        >
                          Pan Number
                        </Typography>
                      </Grid>
                      <Grid item xs="4">
                        {!isFocused ? (
                          <Typography
                            variant="h6"
                            sx={{
                              fontFamily: "sans-serif",
                              paddingLeft: 2,
                            }}
                          >
                            {data.panNumber}
                          </Typography>
                        ) : (
                          <TextField
                            autoFocus
                            variant="standard"
                            value={pannumber}
                            placeholder={data.panNumber}
                            sx={{
                              fontFamily: "sans-serif",
                            }}
                            onChange={(event) =>
                              setPannumber(event.target.value)
                            }
                            InputProps={{
                              style: {
                                fontSize: 20,
                                paddingLeft: 15,
                              },
                            }}
                          />
                        )}
                      </Grid>
                    </Grid>
                    <Grid container sx={{ margin: "1%" }}>
                      <Grid item xs="6">
                        <Box
                          m={1}
                          display="flex"
                          justifyContent="flex-start"
                          alignItems="flex-start"
                        >
                          <Button
                            onClick={() => {
                              setIsFocused(true);
                              // navigate("/bank-details");
                            }}
                            variant="contained"
                            sx={{
                              height: 40,
                              width: 100,
                              backgroundColor: "#005555",
                              ":hover": {
                                color: "white",
                                backgroundColor: "#005555",
                              },
                              margin: "3vh auto",
                            }}
                          >
                            Edit
                          </Button>
                        </Box>
                      </Grid>
                      <Grid item xs="6">
                        <Box
                          m={1}
                          display="flex"
                          justifyContent="flex-start"
                          alignItems="flex-start"
                        >
                          <Button
                            onClick={() => {
                              setIsFocused(false);
                              update();
                              // navigate("/bank-details");
                            }}
                            variant="contained"
                            sx={{
                              height: 40,
                              width: 100,
                              backgroundColor: "#005555",
                              ":hover": {
                                color: "white",
                                backgroundColor: "#005555",
                              },
                              margin: "3vh auto",
                            }}
                          >
                            Update
                          </Button>
                        </Box>
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
  );
}
