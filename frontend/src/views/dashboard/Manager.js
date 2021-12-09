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
  Heading
} from '@chakra-ui/react';
import { UserContext} from '../../providers/UserProvider';

export default function Manager() {
  const user = useContext(UserContext);

  const [schedule, setSchedule] = useState([]);
  const [hotel, setHotel] = useState([]);

  useEffect(() => {
    const { hotelId } = user;

    fetch(`/api/hotels/${hotelId}`)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        setHotel(data);
      });

    fetch(`/api/hotels/${hotelId}/schedule`)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        setSchedule(data);
      });
  }, [user]);

  return (
    <>
    <Center>
      <Heading m={8}>Cleaning Schedule</Heading>
    </Center>
    <Container minWidth='80vw'>
    <Table variant='simple' size='sm'>
      <TableCaption>Cleaning schedule at {hotel.name}</TableCaption>

      <Thead>
        <Tr>
          <Th>Worker id</Th>
          <Th>Worker</Th>
          <Th>Floor</Th>
          <Th>Room</Th>
          <Th>When</Th>
        </Tr>
      </Thead>

      <Tbody>
        {schedule && schedule.map((entry) => {
          console.log(entry);
          return (
            <Tr key={entry.id}>
              <Td>{entry.cleaner.id}</Td>
              <Td>{entry.cleaner.user.name + " " + entry.cleaner.user.surname}</Td>
              <Td>{entry.room.floor}</Td>
              <Td>{entry.room.roomNumber}</Td>
              <Td>{new Date(entry.cleanTime).toLocaleDateString()}</Td>
            </Tr>
          );
        })}
      </Tbody>
    </Table>
    </Container>
    </>
  );
}
