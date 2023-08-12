import React, { useState } from "react";
import { IndexContainer, InnerContainer } from "./component";
import {
  Button,
  Container,
  ProjectBox,
  Title,
  Inner,
  FootContainer,
} from "../../../emotion/component";
import test from "../../../../json/test.json";
const Proboard = () => {
  // 한 페이지에서 보여줄 개수
  const items = 8;
  // 현 페이지를 나타낼 상태
  const [currentPage, setCurrentPage] = useState(1);

  // 보여줄 페이지의 범위
  const startIndex = (currentPage - 1) * items;
  const endIndex = startIndex + items;

  // 페이지 범위에 따라 데이터 분할
  const currentItems = test.Project.slice(startIndex, endIndex);

  // 현 페이지의 상태를 변화 시켜 페이지 이동
  const handlePrevPage = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
    }
  };
  const handleNextPage = () => {
    const totalPages = Math.ceil(test.Project.length / items);
    if (currentPage < totalPages) {
      setCurrentPage(currentPage + 1);
    }
  };
  return (
    <>
      <Container>
        <Title>판매자 게시판</Title>
        <IndexContainer>
          <InnerContainer>
            <Inner>
              {currentItems &&
                currentItems.map((project) => (
                  <ProjectBox
                    key={project.id}
                    title={project.title}
                    content={project.content}
                    tags={project.tags}
                    imageSrc={project.imageSrc}
                  />
                ))}
            </Inner>
          </InnerContainer>
        </IndexContainer>
        <FootContainer>
          <Button onClick={handlePrevPage}>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="24"
              height="24"
              viewBox="0 0 24 24"
              fill="none"
            >
              <path
                d="M8.1649 12.3704L14.7953 18.8001C15.2091 19.2013 16 18.9581 16 18.4297V5.5703C16 5.0419 15.2091 4.7987 14.7953 5.1999L8.1649 11.6296C7.945 11.8427 7.9451 12.1573 8.1649 12.3704Z"
                fill="white"
              />
            </svg>
            이전 페이지
          </Button>

          <Button onClick={handleNextPage}>
            다음 페이지
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
        </FootContainer>
      </Container>
    </>
  );
};

export default Proboard;
