import React, {useContext, useState} from "react";
import {UserContext, UserDispatchContext} from "../providers/UserProvider";
import {
    Avatar,
    Box, Button, Center, Flex,
    FormControl,
    FormLabel,
    Heading,
    HStack,
    Input,
    Select,
    Stack,
    Text, useBreakpointValue,
    useColorModeValue
} from "@chakra-ui/react";
import {useNavigate} from "react-router";

export default function Profile() {
    const user = useContext(UserContext);
    const setUser = useContext(UserDispatchContext);
    const [address, setAddress] = useState(user.address);
    const [homeNumber, setHomeNumber] = useState(user.homePhoneNumber?.number);
    const [idType, setIdType] = useState('Passport');
    const [idNumber, setIdNumber] = useState(user.idNumber);
    const color = useColorModeValue('gray.700', 'white')
    const point = useBreakpointValue({ base: 'xl', md: 'xl' });
    const navigate = useNavigate()


    console.log(user);

    function save() {
        fetch('api/guest/' + user.id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                homePhoneNumber: { id: user.id, number: homeNumber },
                idType,
                idNumber,
                address
            })
        }).then(res => res.json()).then((res) => {
            setUser(res)
        });

    }

    return (
        <Flex
            flex='1'
            align={'center'}
            justify={'center'}
            bg={useColorModeValue('gray.50', 'gray.800')}>
            <Stack spacing={8} mx={'auto'} maxW={'lg'} py={12} px={6}>
                <Stack align={'center'}>
                    <Center>
                        <Avatar
                            size={'2xl'}
                            src={'https://avatars.dicebear.com/api/male/username.svg'}
                        />
                    </Center>
                    <Heading fontSize={'4xl'} textAlign={'center'}>
                        {user.user.name} {user.user.surname}
                    </Heading>
                    <Text fontSize={'lg'} color={'gray.600'}>
                    </Text>
                </Stack>
                <Box
                    rounded={'lg'}
                    bg={useColorModeValue('white', 'gray.700')}
                    boxShadow={'lg'}
                    p={8}>
                    <Stack spacing={4}>
                        <HStack>
                            <Box marginRight='60px'>
                                <FormControl id="hotel">
                                    <FormLabel>Name</FormLabel>
                                    <Text
                                        color={useColorModeValue('gray.700', 'white')}
                                        fontWeight={700}
                                        lineHeight={1.2}
                                        fontSize={useBreakpointValue({ base: 'xl', md: 'xl' })}>
                                        {user.user.name}
                                    </Text>
                                </FormControl>
                            </Box>
                            <Box>
                                <FormControl id="hotel">
                                    <FormLabel>Surname</FormLabel>
                                    <Text
                                        color={useColorModeValue('gray.700', 'white')}
                                        fontWeight={700}
                                        lineHeight={1.2}
                                        fontSize={useBreakpointValue({ base: 'xl', md: 'xl' })}>
                                        {user.user.surname}
                                    </Text>
                                </FormControl>
                            </Box>
                        </HStack>

                        <FormControl id="hotel">
                            <FormLabel>Mobile Number</FormLabel>
                            <Text
                                color={useColorModeValue('gray.700', 'white')}
                                fontWeight={700}
                                lineHeight={1.2}
                                fontSize={useBreakpointValue({ base: 'xl', md: 'xl' })}>
                                {user.mobilePhoneNumber.number}
                            </Text>
                        </FormControl>

                        <FormControl id="hotel">
                            <FormLabel>Email</FormLabel>
                            <Text
                                color={useColorModeValue('gray.700', 'white')}
                                fontWeight={700}
                                lineHeight={1.2}
                                fontSize={useBreakpointValue({ base: 'xl', md: 'xl' })}>
                                {user.user.email}
                            </Text>
                        </FormControl>

                        <FormControl id="address">
                            <FormLabel>Address</FormLabel>
                            {user.address ?
                                <Text
                                    color={color}
                                    fontWeight={700}
                                    lineHeight={1.2}
                                    fontSize={point}>
                                    {user.address}
                                </Text> :
                                <Input value={address} onChange={(e) => setAddress(e.target.value)} type="text" />
                            }
                        </FormControl>

                        <FormControl id="homeNumber">
                            <FormLabel>Home Phone Number</FormLabel>
                            {user.homePhoneNumber ?
                                <Text
                                    color={color}
                                    fontWeight={700}
                                    lineHeight={1.2}
                                    fontSize={point}>
                                    {user.homePhoneNumber.number}
                                </Text> :
                                <Input value={homeNumber} onChange={(e) => setHomeNumber(e.target.value)} type="text"/>
                            }
                        </FormControl>

                        <FormControl id="idType">
                            <FormLabel>Type of Document</FormLabel>
                            {user.idType ?
                                <Text
                                    color={color}
                                    fontWeight={700}
                                    lineHeight={1.2}
                                    fontSize={point}>
                                    {user.idType}
                                </Text> :
                                <Select size="md" value={idType} onChange={(e) => setIdType(e.target.value)}>
                                    <option>Passport</option>
                                    <option>National ID</option>
                                </Select>
                            }
                        </FormControl>

                        <FormControl id="idNumber">
                            <FormLabel>Document Number</FormLabel>
                            {user.idNumber ?
                                <Text
                                    color={color}
                                    fontWeight={700}
                                    lineHeight={1.2}
                                    fontSize={point}>
                                    {user.idNumber}
                                </Text> :
                                <Input value={idNumber} onChange={(e) => setIdNumber(e.target.value)} type="text"/>
                            }
                        </FormControl>


                        {!user.idNumber && <Stack spacing={10} pt={2}>
                            <Button
                                loadingText="Booking"
                                onClick={save}
                                size="lg"
                                bg={'blue.400'}
                                color={'white'}
                                _hover={{
                                    bg: 'blue.500',
                                }}>
                                Save
                            </Button>
                        </Stack>}
                    </Stack>
                </Box>
            </Stack>
        </Flex>
    )
}