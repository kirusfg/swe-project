import React, { useContext }  from 'react';
import { UserContext, UserDispatchContext } from '../providers/UserProvider';
import {
  Flex,
  Box,
  FormControl,
  FormLabel,
  Input,
  Link,
  Stack,
  Switch,
  Button,
  Heading,
  Text,
  useColorModeValue,
  useToast
} from '@chakra-ui/react';
import { useState } from 'react';
import { Link as BrowserLink, useNavigate } from 'react-router-dom';


export default function Login() {
  const setUser = useContext(UserDispatchContext);
  const navigate = useNavigate();
  const toast = useToast();

  const [email, setEmail] = useState("");
  const [employeeLogin, setEmployeeLogin] = useState(false);

  function login(email) {
    const uri = employeeLogin ?
      '/api/auth/employee/login' :
      '/api/auth/login';

    fetch(uri, {
      method: 'POST',
      body: email
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

          setTimeout(() => {
            setUser(data);
            if (employeeLogin) {
              navigate("/dashboard", { replace: true });
            } else {
              navigate("/", { replace: true });
            }
          }, 2000);
        } else {
          toast({
            title: 'Wrong email',
            description: "There is no user with that email",
            status: 'error',
            duration: 3000,
            isClosable: true,
          });
        }
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
          <Heading fontSize={'4xl'} textAlign={'center'}>
            Login
          </Heading>
          <Text fontSize={'lg'} color={'gray.600'}>
            Welcome back!   
          </Text>
        </Stack>
        <Box
          rounded={'lg'}
          bg={useColorModeValue('white', 'gray.700')}
          boxShadow={'lg'}
          p={8}>
          <Stack spacing={4}>
            <FormControl id="email" isRequired>
              <FormLabel>Email address</FormLabel>
              <Input onChange={(e) => setEmail(e.target.value)} type="email" />
            </FormControl>

            <FormControl display='flex' alignItems='center'>
              <FormLabel htmlFor='employee' mb='0'>
                Employee
              </FormLabel>
              <Switch id='employee' size='lg' onChange={() => setEmployeeLogin((prev) => !prev)} />
            </FormControl>

            <Stack spacing={10} pt={2}>
              <Button
                loadingText="Submitting"
                size="lg"
                bg={'blue.400'}
                onClick={() => login(email)}
                color={'white'}
                _hover={{
                  bg: 'blue.500',
                }}>
                Login
              </Button>
            </Stack>
            <Stack pt={6}>
              <Text align={'center'}>
                Don't have an account? <Link as={BrowserLink} to='/register' color={'blue.400'}>Sign up</Link>
              </Text>
            </Stack>
          </Stack>
        </Box>
      </Stack>
    </Flex>
  ); 
}
