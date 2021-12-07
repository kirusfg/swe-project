import React, { useContext } from 'react';
import {
} from '@chakra-ui/react';
import { Navigate } from 'react-router-dom';
import { UserContext} from '../providers/UserProvider';

export default function Dashboard() {
  const user = useContext(UserContext);

  if (user && user.role) {
    return (
      <p>Dashboard</p>
    );
  } else {
    return (
      <Navigate to='/' />
    );
  }
}
