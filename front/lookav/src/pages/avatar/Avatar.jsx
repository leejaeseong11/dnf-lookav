import { Container, Image, Table } from 'react-bootstrap';
import { Url, AvatarSlot } from '../../util/constants/Constants';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';

const Avatar = () => {
  const characterId = useParams().characterId;
  const [viewAvatar, setViewAvatar] = useState({});

  useEffect(() => {
    async function fetchData() {
      await axios
        .get(Url.BACK_URL + 'avatar/' + characterId + '/item')
        .then((res) => {
          const avatarListFromApi = {};
          res.data.forEach((data) => {
            avatarListFromApi[data.slot] = data.itemName;
          });
          Object.keys(AvatarSlot).forEach((avatarSlotName) => {
            setViewAvatar((viewAvatar) => {
              const returnedViewAvatar = { ...viewAvatar };
              let avatarItemName = '없음';
              if (avatarSlotName in avatarListFromApi) {
                avatarItemName = avatarListFromApi[avatarSlotName];
              }
              returnedViewAvatar[AvatarSlot[avatarSlotName]] = avatarItemName;
              return returnedViewAvatar;
            });
          });
        })
        .catch((err) => {
          // todo error handling
          console.log(err.response.data);
        });
    }
    fetchData();
  }, []);

  const avatarImageUrl = Url.AWS_URL + characterId + '.jpg';
  return (
    <Container>
      <Image src={avatarImageUrl} />
      <Table>
        <tbody>
          {Object.keys(viewAvatar).map((slotName, index) => {
            return (
              <tr key={slotName}>
                <th>{slotName}</th>
                <td>{viewAvatar[slotName]}</td>
              </tr>
            );
          })}
        </tbody>
      </Table>
    </Container>
  );
};

export default Avatar;
