import React, { useContext, useEffect, useState } from 'react';
import {
  Center,
  Container,
  Table,
  TableCaption,
  Thead,
  Tbody,
  Tr,
  Td,
  Th,
  Heading,
  useToast
} from '@chakra-ui/react';
import { UserContext} from '../../providers/UserProvider';

export default function Manager() {
  const toast = useToast();

  const user = useContext(UserContext);
  const { hotelId } = user;

  const [reservations, setReservations] = useState([]);
  const [hotel, setHotel] = useState([]);

  
  useEffect(() => {
    fetch(`/api/hotels/${hotelId}`)
      .then((res) => res.json())
      .then((data) => {
        setHotel(data);
      });

    fetch(`/api/hotels/${hotelId}/reservations`)
      .then((res) => res.json())
      .then((data) => {
        setReservations(data);
      });
  }, [user]);


  function book(reservationId, roomId) {
    fetch(`/api/hotels/${hotelId}/book/${reservationId}`, {
      method: 'POST',
      body: JSON.stringify(roomId)
    })
      .then((res) => res.json())
      .then(() => {
        setReservations((prev) => prev.filter((r) => r.id !== reservationId));

        toast({
          title: 'Success',
          description: 'The room was booked',
          status: 'success',
          duration: 3000,
          isClosable: true,
        }); 
      });
  }

  return (
    <>
    <Center>
      <Heading m={8}>Reservations</Heading>
    </Center>
    <Container minWidth='80vw'>
    <Table variant='simple'>
      <TableCaption>Reservations at {hotel.name}</TableCaption>

      <Thead>
        <Tr>
          <Th>Guest</Th>
          <Th>Room type</Th>
          <Th>From</Th>
          <Th>To</Th>
        </Tr>
      </Thead>

      <Tbody>
        {reservations && reservations.map((r) => {
          console.log(r);
          return (
            <Tr key={r.id}>
              <Td>{r.guest.user.name + " " + r.guest.user.surname}</Td>
              <Td>{r.type}</Td>
              <Td>{new Date(r.start).toLocaleDateString()}</Td>
              <Td>{new Date(r.finish).toLocaleDateString()}</Td>
            </Tr>
          );
        })}
      </Tbody>
    </Table>
    </Container>
    </>
  );
}
