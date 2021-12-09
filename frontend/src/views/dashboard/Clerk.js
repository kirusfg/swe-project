import React, { useContext, useEffect, useState } from 'react';
import {
  Button,
  Center,
  Container,
  Modal,
  ModalOverlay,
  ModalHeader,
  ModalCloseButton,
  ModalFooter,
  ModalBody,
  ModalContent,
  Select,
  Table,
  TableCaption,
  Thead,
  Tbody,
  Tr,
  Td,
  Th,
  Heading,
  useToast,
  useDisclosure
} from '@chakra-ui/react';
import { UserContext} from '../../providers/UserProvider';

export default function Manager() {
  const toast = useToast();
  const { isOpen, onOpen, onClose } = useDisclosure();

  const user = useContext(UserContext);
  const { hotelId } = user;

  const [reservations, setReservations] = useState([]);
  const [hotel, setHotel] = useState([]);

  const [reservationId, setReservationId] = useState([]);
  const [floor, setFloor] = useState(1);
  const [maxFloor, setMaxFloor] = useState(1);
  const [room, setRoom] = useState({});

  
  useEffect(() => {
    fetch(`/api/hotels/${hotelId}`)
      .then((res) => res.json())
      .then((data) => {
        setHotel(data);
        setMaxFloor(data.rooms[data.rooms.length - 1].roomNumber[0]);
      });

    fetch(`/api/hotels/${hotelId}/reservations`)
      .then((res) => res.json())
      .then((data) => {
        setReservations(data);
      });
  }, [user, hotelId]);


  function book(reservationId, roomId) {
    fetch(`/api/hotels/${hotelId}/book/${reservationId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(room)
    })
      .then((res) => res.json())
      .then(() => {
        setReservations((prev) => prev.filter((r) => r.id !== reservationId));
        onClose();
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
          <Th></Th>
        </Tr>
      </Thead>

      <Tbody>
        {reservations && reservations.map((r) => {
          return (
            <Tr key={r.id}>
              <Td>{r.guest.user.name + " " + r.guest.user.surname}</Td>
              <Td>{r.type}</Td>
              <Td>{new Date(r.start).toLocaleDateString()}</Td>
              <Td>{new Date(r.finish).toLocaleDateString()}</Td>
              <Td>
                <Button
                  display={{ base: 'none', md: 'inline-flex' }}
                  onClick={() => {
                    setReservationId(r.id);
                    onOpen();
                  }}
                  fontSize={'sm'}
                  fontWeight={600}
                  color={'white'}
                  bg={'blue.400'}
                  href={'#'}
                  _hover={{
                    bg: 'blue.500',
                  }}>
                  Book
                </Button>
              </Td>
            </Tr>
          );
        })}
      </Tbody>
    </Table>
    </Container>

    <Modal isOpen={isOpen} onClose={onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Modal Title</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          Floor
          <Select value={floor} onChange={(e) => setFloor(e.target.value)} placeholder='Select floor'>
            {Array.from({length: maxFloor}, (_, i) => i + 1).map((floor) => {
              return <option value={floor}>{floor}</option>;
            })}
          </Select>       

          Room
          <Select onChange={(e) => setRoom(hotel.rooms.find((room) => room.id == e.target.value))} placeholder='Select room'>
            {hotel.rooms && hotel.rooms.filter((room) => room.floor == floor).map((room) => {
              return <option value={room.id}>{room.roomNumber}</option>;
            })}
          </Select>       
        </ModalBody>

        <ModalFooter>
          <Button colorScheme='blue' mr={3} onClick={() => book(reservationId, room)}>
            Book
          </Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
    </>
  );
}
