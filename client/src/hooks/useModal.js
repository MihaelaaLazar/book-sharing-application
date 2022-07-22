import { useEffect, useState } from "react"

const useModal = ({ Component, initialProps = {} }) => {
    const [isVisible, setVisible] = useState(false);
    const [additionalProps, setAdditionalProps] = useState({});

    useEffect(() => {
        document.body.style.overflow = isVisible ? 'hidden' : 'unset';
    }, [isVisible])

    const toggleModal = (state) => {
        setVisible(state);
    }

    const setModalData = (data) => {
        setVisible(!!data);

        if (data) {
            setAdditionalProps(prevState => ({
                ...prevState,
                ...data
            }))
        }
    }

    let hookPayload = {
        isVisible,
        setModalData
    }

    if (Component) {
        hookPayload = {
            ...hookPayload,
            modal: isVisible ? <Component {...initialProps} {...additionalProps} onClose={() => toggleModal(false)} /> : null,
        }
    }

    return hookPayload;
}

export default useModal;