import * as React from "react";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Link from "@mui/material/Link";
import { useState } from "react";
import InputAdornment from "@mui/material/InputAdornment";
import Box from "@mui/material/Box";
import MailOutlineIcon from "@mui/icons-material/MailOutline";
import { Card } from "@mui/material";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import KeyIcon from "@mui/icons-material/Key";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import img from "../images/swift_image.jpg";
import { useNavigate } from "react-router-dom";
import batonlogo from "../images/baton.png";
import axios from "axios";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const theme = createTheme({
  components: {
    MuiLink: {
      styleOverrides: {
        root: {
          textDecoration: "none",
          ":hover": {
            textDecoration: "underline",
            cursor: "pointer",
          },
        },
      },
    },
  },
});

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [isValid, setIsValid] = useState("");

  const isDisabled = !email || !password || !isValid;

  const handleChange = (event) => {
    const input = event.target.value;
    setEmail(input);
    setIsValid(validateEmail(input));
  };

  const validateEmail = (email) => {
    const regex = /\S+@\S+\.\S+/;
    return regex.test(email);
  };

  const navigate = useNavigate();
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    let mapData = {
      emailId: data.get("email"),
      password: data.get("password"),
    };

    console.log(mapData);

    axios
      .post("https://swiftpay.stackroute.io/user-service/login", mapData)
      .then((res) => {
        if (res.status == 200) {
          console.log(res.data);
          localStorage.setItem("token", res.data);
          navigate("/profile");
        }
        localStorage.setItem("email", mapData.emailId);
      })
      .catch(function (error) {
        toast.error("Enter a valid Email Id or Password");
        console.log(error.response.data);
      });
  };

  return (
    <ThemeProvider theme={theme}>
      <ToastContainer position="top-center" />
      <Card
        style={{
          marginTop: "40px",
          height: "88vh",
          padding: "85px",
          border: "2px solid grey",
          boxShadow: " 10px 10px 5px 1px #005555",
        }}
        sx={{
          my: 8,
          mx: 26,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <div>
          <h1 style={{ fontSize: "40px" }}>
            <img
              src={batonlogo}
              alt="baton_logo"
              style={{ width: "60px", height: "55px" }}
            />
            &nbsp;SwiftPay
          </h1>
        </div>

        <Grid container component="main" sx={{ height: "100vh" }}>
          <CssBaseline />
          <Grid
            item
            xs={false}
            sm={false}
            md={false}
            lg={6}

            // sx={{
            //   backgroundImage: `url(${img})`,
            //   backgroundPosition: 'center',
            //   maxWidth: '200px'
            // }}
          >
            <img
              src={img}
              style={{
                width: "100%",
                height: "90%",
                objectFit: "cover",
              }}
            />
          </Grid>
          <Grid
            item
            xs={12}
            sm={12}
            md={12}
            lg={6}
            square
            style={{ padding: "50px" }}
          >
            <Box>
              <Typography component="h1" variant="h4">
                LOGIN
              </Typography>
              <Box
                component="form"
                noValidate
                onSubmit={handleSubmit}
                sx={{ mt: 1 }}
              >
                <TextField
                  margin="normal"
                  required
                  fullWidth
                  id="email"
                  label="Email Address"
                  name="email"
                  autoComplete="email"
                  value={email}
                  onChange={handleChange}
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start">
                        <MailOutlineIcon />
                      </InputAdornment>
                    ),
                  }}
                />
                <TextField
                  margin="normal"
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start">
                        <KeyIcon />
                      </InputAdornment>
                    ),
                  }}
                />
                <Box sx={{ paddingTop: 2 }}></Box>

                <Button
                  type="submit"
                  variant="contained"
                  id="loginBtn"
                  sx={{ mt: 3, mb: 2, height: 50 }}
                  style={{ backgroundColor: "#005555" }}
                  disabled={isDisabled}
                >
                  Login
                </Button>
                <Grid container>
                  <Grid item>
                    <Link
                      to="/signup"
                      onClick={(e) => {
                        navigate("/signup");
                      }}
                      variant="body2"
                    >
                      {"Don't have an account? Sign Up"}
                    </Link>
                  </Grid>
                </Grid>
              </Box>
            </Box>
          </Grid>
        </Grid>
      </Card>
    </ThemeProvider>
  );
}
