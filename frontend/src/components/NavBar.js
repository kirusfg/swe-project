import {
  Avatar,
  Box,
  Center,
  Flex,
  Text,
  IconButton,
  Button,
  Stack,
  Link,
  Menu,
  MenuButton,
  MenuList,
  MenuItem,
  MenuDivider,
  useColorModeValue,
} from '@chakra-ui/react';
import { Link as BrowserLink } from 'react-router-dom';
import { useContext } from 'react';
import { ColorModeSwitcher } from '../ColorModeSwitcher';
import { Logo } from '../Logo';
import { UserContext, UserDispatchContext  } from '../providers/UserProvider';
import {useNavigate} from "react-router";


export default function NavBar() {
  const user = useContext(UserContext);
  const setUser = useContext(UserDispatchContext);
  const navigate = useNavigate();

  return (
    <>
      <Box bg={useColorModeValue('gray.100', 'gray.900')} px={4}>
        <Flex h={16} alignItems={'center'} justifyContent={'space-between'}>
          <Logo w={8} />

          <Flex alignItems={'center'}>
            <Stack direction={'row'} spacing={7}>
              {user ?
                <Stack spacing={7} direction={'row'}>
                  <Button
                      as={BrowserLink}
                      to='/booking'
                      display={{ base: 'none', md: 'inline-flex' }}
                      fontSize={'sm'}
                      fontWeight={600}
                      color={'white'}
                      bg={'blue.400'}
                      href={'#'}
                      _hover={{
                        bg: 'blue.500',
                      }}>
                    Booking
                  </Button>
                  <Menu>
                    <MenuButton
                        as={Button}
                        rounded={'full'}
                        variant={'link'}
                        cursor={'pointer'}
                        minW={0}>
                      <Avatar
                          size={'sm'}
                          src={'https://avatars.dicebear.com/api/male/username.svg'}
                      />
                    </MenuButton>
                    <MenuList alignItems={'center'}>
                      <br />
                      <Center>
                        <Avatar
                            size={'2xl'}
                            src={'https://avatars.dicebear.com/api/male/username.svg'}
                        />
                      </Center>
                      <br />
                      <Center>
                        <p>{`${user.user.name} ${user.user.surname}`}</p>
                      </Center>
                      <br />
                      <MenuDivider />
                      <MenuItem>Your Servers</MenuItem>
                      <MenuItem>Account Settings</MenuItem>
                      <MenuItem onClick={() => {
                        setUser(undefined)
                        navigate('/');
                      }}>Logout</MenuItem>
                    </MenuList>
                  </Menu>
                </Stack>
                : 
              <Stack
                flex={{ base: 1, md: 0 }}
                justify={'flex-end'}
                direction={'row'}
                spacing={6}>
                <Button
                  as={BrowserLink}
                  to='/login'
                  fontSize={'sm'}
                  fontWeight={400}
                  variant={'link'}
                  href={'#'}>
                  Login
                </Button>
                <Button
                  as={BrowserLink}
                  to='/register'
                  display={{ base: 'none', md: 'inline-flex' }}
                  fontSize={'sm'}
                  fontWeight={600}
                  color={'white'}
                  bg={'blue.400'}
                  href={'#'}
                  _hover={{
                    bg: 'blue.500',
                  }}>
                  Sign Up
                </Button>
              </Stack>
              }
              <ColorModeSwitcher />
            </Stack>
          </Flex>
        </Flex>
      </Box>
    </>
  );
}
