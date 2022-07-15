import {TabWrapper} from "./TabbedComponent.style";
import {useContext} from "react";
import {TabsContext} from "./TabbedComponent";

const Tab = ({index, label, isActive}) => {
    const {onTabChange} = useContext(TabsContext);

    return(
        <TabWrapper isActive={isActive} onClick={() => onTabChange(index)}>
            <span>{label}</span>
        </TabWrapper>
    )
}
export  default Tab;