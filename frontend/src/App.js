import React from 'react';
import { Routes, Route } from 'react-router-dom';
import {
  ChakraProvider,
  Flex,
  theme,
} from '@chakra-ui/react';

import { UserProvider } from './providers/UserProvider'

import NavBar from './components/NavBar';

import Register from './views/Register';
import Login from './views/Login';
import Home from './views/Home';
import Dashboard from './views/dashboard';
import Reservation from "./views/Reservation";
import Profile from "./views/Profile";


function App() {
  return (
    <ChakraProvider theme={theme}>
      <UserProvider>
        <Flex minHeight='100vh' top={0} bottom={0} direction='column'>
        <NavBar />
          <Routes>
            <Route index element={<Home />} />
            <Route path="register" element={<Register />} />
            <Route path="login" element={<Login />} />
            <Route path="dashboard" element={<Dashboard />} />
            <Route path="reserve" element={<Reservation />} />
            <Route path="profile" element={<Profile />}/>
          </Routes>
        </Flex>
      </UserProvider>
    </ChakraProvider>
  );
}

export default App;
