import {useEffect, useRef, useState} from "react";
import {Container, IconMagnifyingGlass, IconRightArrow, SearchInput} from "./Search.style";
import {faMagnifyingGlass, faArrowRight} from '@fortawesome/free-solid-svg-icons';
import BookApi from "../reusable/apis/bookApi";
import {useNavigate} from "react-router-dom";

function Search() {
    const targetRef = useRef(null);
    const [isHovered, setIsHovered] = useState(false);
    const [isFocused, setIsFocused] = useState(false);
    const showSearchInput = isHovered || isFocused;
    const [searchQuery, setSearchQuery] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        targetRef.current.value = "";
    }, [showSearchInput]);

    const handleChange = (e) => {
        setSearchQuery(e.target.value);
    }
    const handleSearch = async () => {
        setSearchQuery(null)
        navigate(`/search/${searchQuery}`);
    }

    return (
            <Container
                onMouseEnter={() => setIsHovered(true)}
                onMouseLeave={() => setIsHovered(false)}
                onFocus={() => setIsFocused(true)}
                onBlur={() => setIsFocused(false)}
                hover={showSearchInput}
            >
                <SearchInput placeholder="Search..." onChange={handleChange} ref={targetRef} showSearchInput={showSearchInput}/>
                {showSearchInput
                    ? <IconRightArrow onClick={handleSearch}  icon={faArrowRight}/>
                    : <IconMagnifyingGlass  icon={faMagnifyingGlass}/>}
            </Container>
    );
}

export default Search;
