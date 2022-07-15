import {TabbedComponentWrapper, TabContent, TabsWrapper} from "./TabbedComponent.style";
import {createContext, useState} from "react";
import Tab from "./Tab";

export const TabsContext = createContext({});

const TabbedComponent = ({tabs, initialActiveTab = 0 }) => {
    const [activeTab, setActiveTab] = useState(initialActiveTab);

    const onTabChange = (value) => {
        setActiveTab(value);
    }
    const value = {
        activeTab,
        onTabChange
    }

    return (
        <TabsContext.Provider value={value}>
            <TabbedComponentWrapper>
                <TabsWrapper>
                    {
                        tabs.map((tab, index) => {
                            return <Tab key={index}
                                        index = {index}
                                        label={tab.label}
                                        isActive={index === activeTab}
                            />
                        })
                    }
                </TabsWrapper>
                {
                    tabs?.length > 0 && tabs[activeTab]?.content && <TabContent>
                        {tabs[activeTab].content}
                    </TabContent>
                }
            </TabbedComponentWrapper>
        </TabsContext.Provider>
    )
}
export default TabbedComponent;