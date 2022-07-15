import styled from "styled-components";

export const TabbedComponentWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`
export const TabsWrapper = styled.div`
  display: flex;
  flex-direction: row;
  border-bottom: 1px solid rgba(0,0,0, 0.1);
  justify-content: space-between;
`
export const TabContent = styled.div`
  padding: 1rem;
`
export const TabWrapper = styled.div`
  display: flex;
  flex:1 1;
  justify-content: center;
  align-items: center;
  padding: 1rem 2rem;
  border-bottom: ${({isActive}) => isActive ? "1px solid #de411b" : "none"};
  cursor: pointer;
  
  & > span {
    color:  #1e3a61;
    font-size: 20px;
    font-weight: 600;
  }
`


