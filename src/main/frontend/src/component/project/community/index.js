import React, { useState } from "react";
import {
  Container,
  Title,
  ButtonContainer,
  Button, Inners,
} from "../../emotion/component";
import { OuterContainer, TableContainer } from "./component";
const Community = () => {
  const [data, setData] = useState([
    { id: 1, title: "Sample Title 1", author: "John" },
    { id: 2, title: "Sample Title 2", author: "Jane" },
    { id: 3, title: "Sample Title 3", author: "Mike" },
    { id: 4, title: "Sample Title 4", author: "Emily" },
    { id: 5, title: "Sample Title 5", author: "Alice" },
    { id: 6, title: "", author: "" },
    { id: 7, title: "", author: "" },
    { id: 8, title: "", author: "" },
    { id: 9, title: "", author: "" },
    { id: 10, title: "", author: "" },
  ]);

  const handleTitleChange = (index, value) => {
    setData((prevData) =>
      prevData.map((item, i) =>
        index === i ? { ...item, title: value } : item
      )
    );
  };

  const handleAuthorChange = (index, value) => {
    setData((prevData) =>
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
            <Button>글쓰기</Button>
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
              {data.map((item, index) => (
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
        <Button>
          다음페이지
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
          >
            <path
              d="M15.8351 11.6296L9.20467 5.1999C8.79094 4.79869 8 5.04189 8 5.5703V18.4297C8 18.9581 8.79094 19.2013 9.20467 18.8001L15.8351 12.3704C16.055 12.1573 16.0549 11.8427 15.8351 11.6296Z"
              fill="white"
            />
          </svg>
        </Button>
      </Inners>
    </>
  );
};

export default Community;
