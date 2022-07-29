import WaitingListApi from "../../reusable/apis/waitingListApi";
import {useSelector} from "react-redux";
import {useEffect, useState} from "react";
import WaitingCard from "./WaitingCard";

const WaitingList = () => {
    const user = useSelector(state => state.user)
    const [waitingList, setWaitingList] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            const res = await WaitingListApi.getInfoByUserId(user.userId);
            setWaitingList(res);
        }
        fetchData();
    }, []);


    return <WaitingCard
                book={waitingList}
            />

}
export default WaitingList;