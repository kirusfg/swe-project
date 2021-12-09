import React, { useContext } from 'react';
import {
} from '@chakra-ui/react';
import { Navigate } from 'react-router-dom';
import { UserContext} from '../../providers/UserProvider';

import Manager from './Manager';
import Clerk from './Clerk';

export default function Dashboard() {
  const user = useContext(UserContext);

  if (user && user.role) {
    if (user.role === 'Manager') {
      return <Manager />
    } else if (user.role === 'Clerk') {
      return <Clerk />
    } else {
      <Navigate to='/' />
    }
  } else {
    return (
      <Navigate to='/' />
    );
  }
}
