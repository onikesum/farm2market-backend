import React, {useEffect, useState} from "react";
import {
  Container,
  Title,
  ButtonContainer,
  Button, Inners,
  OuterContainer,
} from "../../emotion/component";
import {  TableContainer } from "./component";
import {Link} from "react-router-dom";
import axios from "axios";
const Community = () => {
  const [Array,setArray] = useState([]);

  useEffect(() => {
    axios.get('/all')
        .then(response => {
          const dataArray = response.data; // Assuming the response data is an array
          setArray(dataArray);
          console.log(dataArray);
        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });
  }, []);

  const handleTitleChange = (index, value) => {
    setArray((prevData) =>
      prevData.map((item, i) =>
        index === i ? { ...item, title: value } : item
      )
    );
  };

  const handleAuthorChange = (index, value) => {
    setArray((prevData) =>
      prevData.map((item, i) =>
        index === i ? { ...item, author: value } : item
      )
    );
  };

  return (
    <>
      <Inners>
        <OuterContainer>
          <Title>커뮤니티</Title>
          <ButtonContainer>
            <Link to={'/Inquiry/wirte'}>
              <Button>글쓰기</Button>
            </Link>
            <Button>내가쓴글</Button>
          </ButtonContainer>
        </OuterContainer>
        <TableContainer>
          <table>
            <thead>
              <tr>
                <th>제목</th>
                <th>글쓴이</th>
              </tr>
            </thead>
            <tbody>
              {Array.map((item, index) => (
                <tr key={index}>
                  <td>
                    <input
                      type="text"
                      value={item.title}
                      onChange={(e) => handleTitleChange(index, e.target.value)}
                    />
                  </td>
                  <td>
                    <input
                      type="text"
                      value={item.author}
                      onChange={(e) =>
                        handleAuthorChange(index, e.target.value)
                      }
                    />
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </TableContainer>
      </Inners>
    </>
  );
};

export default Community;
