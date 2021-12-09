import React, {useContext, useEffect, useState} from 'react';
import {
    Box, Button,
    Flex, FormControl, FormLabel, Heading, HStack, Input, Select, Stack, Text,
    useColorModeValue, useToast
} from '@chakra-ui/react';
// import {DatePicker} from "@orange_digital/chakra-datepicker";
import InputMask from "react-input-mask";
import {UserContext} from "../providers/UserProvider";
import {Navigate, useNavigate} from "react-router-dom";


export default function Reservation() {
    const [addresses, setAdresses] = useState([]);
    const [hotel, setHotel] = useState();
    const [type, setType] = useState('Single');
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const user = useContext(UserContext);
    const toast = useToast();
    const navigate = useNavigate();


    useEffect(()=> {
        if(!user) {
            navigate('/');
        } else {
            fetch('/api/hotels/').then(res => res.json()).then(res => {
                const arr = res.map(hotel => ({id: hotel.id, value: (hotel.name + ', ' + hotel.address)}));
                setAdresses(arr);
                setHotel(arr[0].value);
            })
        }
    }, [])

    useEffect(() => {
        console.log(hotel);
    }, [hotel])

    function getTimeStamp(date) {
        const myDate = date.split("/");
        const newDate = new Date( myDate[2], myDate[1] - 1, myDate[0]);
        return newDate.toISOString();
    }

    function validate() {
        if(!startDate.length || !endDate.length || startDate.includes('_') || endDate.includes('_')) return false;
        return true;
    }

    function book() {
        if (!validate()) {
            toast({
                title: 'Fill all inputs',
                description: "",
                status: 'error',
                duration: 3000,
                isClosable: true,
            });
            return;
        }
        const id = addresses.find(h => hotel === h.value)?.id;
        fetch(`/api/hotels/${id}/reserve`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                type,
                guest: {
                    id: user.id
                },
                start: getTimeStamp(startDate),
                finish: getTimeStamp(endDate)
            })
        })
            .then((res) => res.json())
            .then((data) => {
                if (!data.status) {
                    toast({
                        title: 'Success',
                        description: 'You will be redirected in a bit',
                        status: 'success',
                        duration: 3000,
                        isClosable: true,
                    });

                    setStartDate('');
                    setEndDate('');
                    setType('Single');
                    setHotel(addresses[0]?.value)
                } else {
                    toast({
                        title: 'Error',
                        description: 'Some unhandled error occured',
                        status: 'error',
                        duration: 3000,
                        isClosable: true,
                    });
                }
        })
    }

    return (
        <Flex
            flex='1'
            align={'center'}
            justify={'center'}
            bg={useColorModeValue('gray.50', 'gray.800')}>
            <Stack spacing={8} mx={'auto'} maxW={'lg'} py={12} px={6}>
                <Stack align={'center'}>
                    <Heading fontSize={'4xl'} textAlign={'center'}>
                        Reservation
                    </Heading>
                    <Text fontSize={'lg'} color={'gray.600'}>
                        Just pick a hotel and reserve a room
                    </Text>
                </Stack>
                <Box
                    rounded={'lg'}
                    bg={useColorModeValue('white', 'gray.700')}
                    boxShadow={'lg'}
                    p={8}>
                    <Stack spacing={4}>
                        <FormControl id="hotel" isRequired>
                            <FormLabel>Hotel</FormLabel>
                            <Select size="md" value={hotel} onChange={(e) => {
                                setHotel(e.target.value)
                            }}>
                                {addresses.map(address => <option>{address.value}</option>)}
                            </Select>
                        </FormControl>

                        <FormControl id="type" isRequired>
                            <FormLabel>Type of Room</FormLabel>
                            <Select size="md" value={type} onChange={(e) => setType(e.target.value)}>
                                <option>Single</option>
                                <option>Double</option>
                            </Select>
                        </FormControl>

                        <HStack>
                            <Box>
                                <FormControl id="startDate" isRequired>
                                    <FormLabel>From</FormLabel>
                                    <Input as={InputMask} value={startDate} mask='99/99/9999' maskChar='_' onChange={(e) => setStartDate(e.target.value)}/>
                                </FormControl>
                            </Box>
                            <Box>
                                <FormControl id="endDate" isRequired>
                                    <FormLabel>To</FormLabel>
                                    <Input as={InputMask} value={endDate} mask='99/99/9999' maskChar='_' onChange={(e) => setEndDate(e.target.value)}/>
                                </FormControl>
                            </Box>
                        </HStack>

                        <Stack spacing={10} pt={2}>
                            <Button
                                loadingText="Booking"
                                onClick={() => book()}
                                size="lg"
                                bg={'blue.400'}
                                color={'white'}
                                _hover={{
                                    bg: 'blue.500',
                                }}>
                                Book
                            </Button>
                        </Stack>
                    </Stack>
                </Box>
            </Stack>
        </Flex>
    )
}
