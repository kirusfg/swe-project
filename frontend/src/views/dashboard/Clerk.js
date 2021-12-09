import React, { useContext } from 'react';
import {
} from '@chakra-ui/react';
import { Navigate } from 'react-router-dom';
import { UserContext} from '../../providers/UserProvider';

export default function Clerk() {
  const user = useContext(UserContext);

  return (
    <p>Clerk dashboard</p>
  );
}
