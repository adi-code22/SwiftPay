import * as React from "react";
import { styled, useTheme } from "@mui/material/styles";
import Box from "@mui/material/Box";
import MuiDrawer from "@mui/material/Drawer";

import List from "@mui/material/List";
import CssBaseline from "@mui/material/CssBaseline";
import Divider from "@mui/material/Divider";
import IconButton from "@mui/material/IconButton";

import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import Person2Icon from "@mui/icons-material/Person2";
import AccountBalanceIcon from "@mui/icons-material/AccountBalance";
import MoveUpIcon from "@mui/icons-material/MoveUp";
import HistoryIcon from "@mui/icons-material/History";
import { useNavigate } from "react-router-dom";
import { useAppStore } from "../appStore";
import AddchartIcon from '@mui/icons-material/Addchart';

const drawerWidth = 240;

const openedMixin = (theme) => ({
  width: drawerWidth,
  transition: theme.transitions.create("width", {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.enteringScreen,
  }),
  overflowX: "hidden",
});

const closedMixin = (theme) => ({
  transition: theme.transitions.create("width", {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  overflowX: "hidden",
  width: `calc(${theme.spacing(7)} + 1px)`,
  [theme.breakpoints.up("sm")]: {
    width: `calc(${theme.spacing(8)} + 1px)`,
  },
});

const DrawerHeader = styled("div")(({ theme }) => ({
  display: "flex",
  alignItems: "center",
  justifyContent: "flex-end",
  padding: theme.spacing(0, 1),
  // necessary for content to be below app bar
  ...theme.mixins.toolbar,
}));

const Drawer = styled(MuiDrawer, {
  shouldForwardProp: (prop) => prop !== "open",
})(({ theme, open }) => ({
  width: drawerWidth,
  flexShrink: 0,
  whiteSpace: "nowrap",
  boxSizing: "border-box",
  ...(open && {
    ...openedMixin(theme),
    "& .MuiDrawer-paper": openedMixin(theme),
  }),
  ...(!open && {
    ...closedMixin(theme),
    "& .MuiDrawer-paper": closedMixin(theme),
  }),
}));

export default function Sidenav() {
  const theme = useTheme();
  // const [open, setOpen] = React.useState(true);
  const navigate = useNavigate();
  const open = useAppStore((state) => state.dopen);

  return (
    <Box sx={{ display: "flex" }}>
      <CssBaseline />
      <Box height={30} />
      <Drawer variant="permanent" open={open} PaperProps={{
    sx: {
      backgroundColor: "#005555",
      color: "#fff",
    }}}>
        <DrawerHeader>
          <IconButton>
            {theme.direction === "rtl" ? (
              <ChevronRightIcon />
            ) : (
              <ChevronLeftIcon />
            )}
          </IconButton>
        </DrawerHeader>
        <Divider />
        <List>
          <ListItem
            
            sx={{ display: "block" }}
            onClick={() => {
              navigate("/profile");
            }}
          >
            <ListItemButton
              sx={{
                minHeight: 70,
                justifyContent: open ? "initial" : "center",
                px: 2.5,
              }}
            >
              <ListItemIcon
                sx={{
                  color: 'white',
                  minWidth: 0,
                  mr: open ? 3 : "auto",
                  justifyContent: "center",
                }}
              >
                <Person2Icon />
              </ListItemIcon>
              <ListItemText primary="Profile" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
          </ListItem>
          <ListItem
            
            sx={{ display: "block" }}
            onClick={() => {
              navigate("/bank-details");
            }}
          >
            <ListItemButton
              sx={{
                minHeight: 70,
                justifyContent: open ? "initial" : "center",
                px: 2.5,
              }}
            >
              <ListItemIcon
                sx={{
                  color: 'white',
                  minWidth: 0,
                  mr: open ? 3 : "auto",
                  justifyContent: "center",
                }}
              >
                <AccountBalanceIcon />
              </ListItemIcon>
              <ListItemText
                primary="Bank Details"
                sx={{ opacity: open ? 1 : 0 }}
              />
            </ListItemButton>
          </ListItem>
          <ListItem
            
            sx={{ display: "block" }}
            onClick={() => {
              navigate("/transfer");
            }}
          >
            <ListItemButton
              sx={{
                minHeight: 70,
                justifyContent: open ? "initial" : "center",
                px: 2.5,
              }}
            >
              <ListItemIcon
                sx={{
                  color: 'white',
                  minWidth: 0,
                  mr: open ? 3 : "auto",
                  justifyContent: "center",
                }}
              >
                <MoveUpIcon />
              </ListItemIcon>
              <ListItemText primary="Transfer" sx={{ opacity: open ? 1 : 0 }} />
            </ListItemButton>
          </ListItem>
          <ListItem
            
            sx={{ display: "block" }}
            onClick={() => {
              navigate("/transactions");
            }}
          >
            <ListItemButton
              sx={{
                minHeight: 70,
                justifyContent: open ? "initial" : "center",
                px: 2.5,
              }}
            >
              <ListItemIcon
                sx={{
                  color: 'white',
                  minWidth: 0,
                  mr: open ? 3 : "auto",
                  justifyContent: "center",
                }}
              >
                <HistoryIcon />
              </ListItemIcon>
              <ListItemText
                primary="Transactions"
                sx={{ opacity: open ? 1 : 0 }}
              />
            </ListItemButton>
          </ListItem>
          
        </List>
      </Drawer>
    </Box>
  );
}
